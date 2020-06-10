# k8s监控

## 指标工具
主流的量度有如下两个工具可以提供
* kube-state-metrics
* metric-server

### 区别

* metric-server（或heapster）是从api-server中获取cpu、内存使用率这种监控指标，并把他们发送给存储后端，如influxdb或云厂商，他当前的核心作用是：为HPA等组件提供决策指标支持。
* kube-state-metrics关注于获取k8s各种资源的最新状态，如deployment或者daemonset，之所以没有把kube-state-metrics纳入到metric-server的能力中，是因为他们的关注点本质上是不一样的。metric-server仅仅是获取、格式化现有数据，写入特定的存储，实质上是一个监控系统。而kube-state-metrics是将k8s的运行状况在内存中做了个快照，并且获取新的指标，但他没有能力导出这些指标
* 换个角度讲，kube-state-metrics本身是metric-server的一种数据来源，虽然现在没有这么做。
* 另外，像Prometheus这种监控系统，并不会去用metric-server中的数据，他都是自己做指标收集、集成的（Prometheus包含了metric-server的能力），但Prometheus可以监控metric-server本身组件的监控状态并适时报警，这里的监控就可以通过kube-state-metrics来实现，如metric-serverpod的运行状态。

### 安装

```bash
cd k8s-monitor
kubectl create -f kube-state-metrics
kubectl create -f metrics-server
```

## 监控工具
### prometheus
