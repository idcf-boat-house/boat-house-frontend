# UI自动化测试

目前项目采用.netcore+xunit+selenium+docker+jenkins,将来还可以采用其他方案

## 插件依赖

测试运行后会生成测试报告，测试报告需要使用jenkins的MsTest插件，才能在测试运行后将报告上传到jenkins中，上传后可以在流水线中查看测试结果，具体上传测试报告的流水线代码，可以参考下方代码

> mstest testResultsFile:"selenium/**/*.trx", keepLongStdio: true