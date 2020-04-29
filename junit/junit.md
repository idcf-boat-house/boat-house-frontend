### 单元测试的编写与自动化

#### 单元测试的意义

> 单元测试属性概念

单元测试可以理解为当一个开发人员开发一个class类或者是新增加已有的class类下的功能方法时,那么这个开发人员就需要对新开发的class类或者是class类下的功能实现的方法负责,保证实现的功能可以满足需求且能够正常运行交付,因此为了达到上述的目标,我们需要对class类或class类下的方法进行单元测试,那么对于一个单元测试具备的属性主要包含如下:

- **单元**是软件系统组成的一个最小单位.
- 满足输入,计算处理,输出三个核心步骤,即我们需要定义输入的信息,通过程序的加工计算处理(也就是我们实现的代码程序),输出就是返回处理结果,测试本身是对于返回的处理结果进行重复验证,保证其正确率不低于我们预设的一个阀值百分比.
- 由单一的开发人员负责完成交付,实现的功能规模小,逻辑简单且相互独立, 能够更容易实现程序完整功能的集成.

> 单元测试作用

在极限编程的TDD流程中,驱动流程前进的开发周期称为“红灯-绿灯-重构”,其中红绿灯体现一个单元测试的失败与成功标识,红灯代表处于说明测试不通过需要被修复或者正处于编写测试状态,绿灯代表实现的功能全部测试通过.TDD流程可以根据红绿灯显示的状态结果来不断重复优化我们的程序代码,对此单元测试对于软件系统,开发人员具备有以下的意义:

- 对于开发人员而言,由于测试规模小且功能独立,因而在不需要考虑外部因素引入的情况下,能够通过单元测试快速定位错误并实现程序代码不断迭代优化,促使测试状态变更为绿灯状态.
- 对于软件系统而言,由于功能独立且规模小,那么在系统功能集成之前(尤其是大型软件系统)进行单元测试有助于提升系统功能实现的正确率,缩小排查异常的范围以及消耗的时间,提升需求功能实现的交付速度.

#### 单元测试的工具使用

通过上述我们已经了解到单元测试的重要意义,基于单元测试的属性特征,进行单元测试的成本并不高,为了响应快速交付的目标,接下来讲述如何实现利用好工具进行单元测试.

- git在开发中的使用

```bash
## 先在github上fork下
## 拉取项目
git clone https://github.com/yourGithubId/boat-house.git

## 配置同步远程仓库
git remote -v
git remote add upstream https://github.com/idcf-boat-house/boat-house.git

## 更新到本地
git fetch upstream && git checkout master && git pull && git merge upstream/master

## 开发需求
## 基于小组owner成员的master
git checkout master && git pull && git checkout -b feature-xxxx

## 完成需求的开发
git pull && git add files && git commit -m "add feature xxx" && git push origin feature-xxxx

## 完成功能验证通过之后在github提PR合并到boat-house的master分支

## 完成PR提交到小组owner的master
git checkout master && git pull && git merge feature-xxxx

## 有冲突修复然后提交
git pull && git add confilctfiles && git commit -m "fixing conflict" && git push origin master
```

- git使用细节(结合idea)

![](../images/junit/boathouse-structure-junit-git-use1.jpg)

提交的时候上面有四个细节,一个是填写开发者名称以及邮件,一般就是name <email>, 第二个就是右边的勾选项,会对我们编写的代码做一些格式优化调整与检查,第三个就是填提交信息一般是简述自己做的事情,最后一个的话,很重要就是提交之前要review自己的代码,避免引入一些无关的代码,比如下面:

![](../images/junit/boathouse-structure-junit-git-use2.jpg)

很有可能自己提交之前将英文逗号给改成中文逗号,但是自己没有检查就会报错.review的好处就是避免多次提交同时减少自己在一些可避免的错误上浪费时间.

- maven在开发中使用

```bash
## maven其他命令请参考相关资源
## 使用脚手架进行快速构建项目
cd /path/project
mvn archetype:create-from-project
cd /target/generated-sources/archetype
mvn install

## 从构建的脚手架快速项目
mvn archetype:generate -DarchetypeCatalog=local

## maven的打包测试
## -Dmaven.test.failure.ignore=true表示单元测试失败仍然不影响打包
## -Dmaven.test.skip=true表示跳过单元测试
## -Pdev dev表示maven传递激活的环境
mvn clean package -Dmaven.test.failure.ignore=true
```

- idea使用

打开idea的Preferences,搜索templates,找到File And Code Templates栏目,点击该栏目找到右侧的Junit4Class的模板配置,可以根据个人情况进行配置.

![image-20200417142513605](../images/junit/boathouse-structure-junit-idea-config.png)

可以为本地的模板配置为一个基本SpringBoot的测试模板样例,如下模板代码:

```java
// 配置Junit4 Test Class模板
/**
 * date:${DATE} ${TIME}
 * author: authorName   ## 开发者名称
 * desc: junit4 test case for ${NAME} 
 */
#parse("File Header.java")
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration()
//@ActiveProfiles("dev") 
@SpringBootTest
public class ${NAME} {
  
    @BeforeClass
    public static void start() throws Exception {
       // 这个方法仅在初始化的时候执行一次
        ${BODY}
    }
    
    @AfterClass
    public static void shutdown() throws Exception {
        // 这个方法在所有测试完成之后会执行一次
        ${BODY}
    } 
    
    ${BODY}   
}

// 配置Junit Test Method 模板
// 测试方法名称为testMethodName
@Test
public void test${NAME}() {
   ${BODY}
}
```

- 使用IDEA快速创建一个单元测试类

选中被测试的类,右键Go To -> Test (同时可以把快捷键记住)

![boathouse-structure-junit-idea-create1](../images/junit/boathouse-structure-junit-idea-create1.png)

这个时候弹出框构建单元测试类,选择Junit4的测试类库,同时按照上述规范命名测试类名称,最后根据实际需求情况勾选要进行单元测试的方法以及对应的setUp和tearDown方法,同时注意生成的测试类存放的包目录,一般情况下只需要勾选要进行测试的方法即可.

![boathouse-structure-junit-idea-create2](../images/junit/boathouse-structure-junit-idea-create2.png)

最后就生成一个基于模板配置好的单元测试类并编写一个单元测试用例如下:

```java
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration()
//@ActiveProfiles("dev")   // 使用dev环境进行测试
@SpringBootTest
public class JdbcUtilsTest {
   
    @Before
    public void setUp()  {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testFindModeResult() throws Exception {
    }
}
```

- Java Code Coverage测试覆盖率测量工具(可选)

```xml
<!-- maven 配置 -->
 <dependency>
   <groupId>org.jacoco</groupId>
   <artifactId>org.jacoco.agent</artifactId>
   <version>0.8.5</version>
   <scope>test</scope>
</dependency>

<!-- maven plugin插件配置 -->
 <plugin>
   <groupId>org.apache.maven.plugins</groupId>
   <artifactId>maven-surefire-plugin</artifactId>
 </plugin>

<!-- 配置jacoco插件 -->
<plugin>
  <groupId>org.jacoco</groupId>
  <artifactId>jacoco-maven-plugin</artifactId>
  <version>0.8.5</version>
  <configuration>
    <rules>
      <rule implementation="org.jacoco.maven.RuleConfiguration">
        <element>BUNDLE</element>
        <limits>
          <!-- 指定方法覆盖50% -->
          <limit implementation="org.jacoco.report.check.Limit">
            <counter>METHOD</counter>
            <value>COVEREDRATIO</value>
            <minimum>0.50</minimum>
          </limit>
          <!-- 指定分支覆盖到50% -->
          <limit implementation="org.jacoco.report.check.Limit">
            <counter>BRANCH</counter>
            <value>COVEREDRATIO</value>
            <minimum>0.50</minimum>
          </limit>
          <!-- 指定CLASS覆盖100% -->
          <limit implementation="org.jacoco.report.check.Limit">
            <counter>CLASS</counter>
            <value>MISSEDCOUNT</value>
            <maximum>0</maximum>
          </limit>
        </limits>
      </rule>
    </rules>
  </configuration>
  <executions>
    <execution>
      <id>pre-test</id>
      <goals>
        <goal>prepare-agent</goal>
      </goals>
      <configuration>
        <!--  生成数据报告文件 -->
        <destFile>${project.build.directory}/coverage-reports/jacoco-it.exec</destFile>
      </configuration>
    </execution>
    <execution>
      <id>check</id>
      <goals>
        <goal>check</goal>
      </goals>
    </execution>
    <execution>
      <!-- 测试阶段输出覆盖率报告 -->
      <id>post-test</id>
      <phase>test</phase>
      <goals>
        <goal>report</goal>
      </goals>
      <configuration>
        <!--  读取数据报告文件,打印测试覆盖率报告文件并以html展示 --> 
        <dataFile>${project.build.directory}/coverage-reports/jacoco-it.exec</dataFile>
        <outputDirectory>${project.reporting.outputDirectory}/jacoco-it</outputDirectory>
      </configuration>
    </execution>
  </executions>
</plugin>
```

上述配置之后,根据maven构建的生命周期,可以直接执行test之后(包含test)命令将输出测试覆盖率报告,即

```bash
## 执行测试
mvn clean test

## 测试报告输出路径
/path/project/target/site/jacoco-it

## 比如找到下面的路径的html(这个是我本地)
/home/idcf-house/boat-house/product-service/api/target/site/jacoco-it
```

打开输出目录的index.html查看执行覆盖率报告结果:

![boathouse-structure-junit-javacoco-report](../images/junit/boathouse-structure-junit-javacoco-report.png)

通过上述报告结果,说明当前进行单元测试的覆盖率只有2%,开发人员需要重新审核自身编写的测试代码情况,即是否测试全面.

- 模拟框架(可选)

当我们需要使用到第三方依赖接口或者是外部资源的时候,为了方便我们能够进行正常测试,在这里需要引入框架来解决依赖的外部资源,其中模拟框架有:

1. Mockito: 官网地址, https://site.mockito.org/

2. EasyMock: 官网地址, https://easymock.org/

3. PowerMock: 这个主要是针对上述两者之一存在无法提供模拟部分特殊类或者方法或者字段支持,是一个功能更为全面且提供模拟测试对象的框架,同时使用到这个框架也意味着开发人员的代码很可能会存在一些不足,需要有其他开发人员协助审查代码质量,更多详细的信息可查阅github学习,https://github.com/powermock/powermock

#### Junit框架简介

- 单元测试框架类图结构设计

![boathouse-structure-junit-Class](../images/junit/boathouse-structure-junit-Class.png)

```java
// Assert: 负责对单元测试结果进行校验
// TestResult: 单元测试输出结果
// TestCase: 开发人员编写的单元测试用例
// TestSuite: 测试集,相对地,如果添加一个单元测试类,则存在在同一个包下的所有单元测试类组成一个单元测试集合,如果是添加一个测试方法,则将所有方法存放在同一个类下组成的一个单元测试类也可以称为一个测试集
// Test: Junit框架提供测试接口
// TestRunner: 如果一个单元测试集(类)添加TestRunner/RunWith,如RunWith(SpringJUnit4ClassRunner.class),说明当前测试集/类/方法引入Spring相关的功能进行单元测试.
```

- Junit框架说明

```java
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TomcatTest {
  
  @BeforeClass
  public static void init(){
      // 在执行测试类中进行初始化操作,整个过程仅执行一次且声明为static
  }
  
  @Before
  public void setUp(){
     // 在每次测试用例执行之前的操作
  }
  
  @Ignore  // 忽略当前的测试用例
  @Test
  public void testCase(){
     // 执行测试用例
  }
  
  @After
  public void tearDown(){
     // 在每次测试用例执行完成之后的后续操作
  }
  
  @AfterClass
  public static void release(){
    // 在所有测试用例执行完成之后的后续操作,整个过程只执行一次且声明为静态static
  }
}
```

- junit框架额外知识(可选)

```java
// @FixMethodOrder: 决定单元测试方法的执行顺序,顺序规则有以下三种:
// MethodSorters.DEFAULT: 使用方法名称的hashcode的顺序执行测试用例
// MethodSorters.NAME_ASCENDING: 使用方法名称顺序执行测试用例
// MethodSorters.JVM: 使用JVM返回的方法名称顺序

// 单元测试之参数化
// 声明处于参数化环境
@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.DEFAULT)
public class ParameterClassTest {
    
    // 构造参数化数据
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
      return Arrays.asList(new Object[][]{{1, 2}});
    }
  
    //开始测试用例
}
```

#### 单元测试实践

在进行单元测试实践之前,我们需要先了解下单元测试的准则与规范

- 单元测试准则: [参考准则英文版]( https://petroware.no/unittesting.html)  、[参考准则中文版](https://github.com/yangyubo/zh-unit-testing-guidelines/blob/master/readme.rst)
- 单元测试规范

```reStructuredText
命名规范: 可以通过上述定义模板来自动生成
1. 测试类名称: ClassNameTest,即在原有的类中添加Test表示要进行单元测试的测试类
2. 方法名称:  @BeforeClass对应的方法名称为init,表示初始化操作.
             @AfterClass对应的方法名称为release,表示执行完所有测试用例之后要释放资源
             @Before对应的方法名称为setUp,@After对应方法名称为tearDown
             @Test对应的测试用例方法名称为testMethodName
3. 单元测试集: 存放单元测试的测试类的包命名规范与被测试的类所处的包目录结构一致,如
java/
	com.idcf.boathouse.mapper.OrderMapper
test/
	com.idcf.boathouse.mapper.OrderMapperTest
```

- 基于需求的单元测试实践

以订单需求为例,现在的需求是开发一个商家接单的功能,对于一个订单的基本流程有: 下单 - 支付  -  接单  -  确认订单  -  订单完成等流程,因此对商家接单功能进行单元测试实践如下:

1. 第一步是编写单元测试用例.

```java
public class OrdersMapperTest {
  
   //由于mapper还没有实现,对此使用Mock框架模拟实现
   // 1. 这个时候还没有定义OrdersMapper,显示红灯,未定义错误
   // 2. 编写测试用例完成之后再定义OrdersMapper,定义并修复红灯
   @Mock
   private OrdersMapper ordersMapper;
   
   @Before
    public void setUp() throws Exception {
        // 在每个测试方法之前都会执行
        //进行初始化操作
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        // 这个方法每个测试方法执行之后都会执行
    }
  
    @Test
    public void testConfirmOrder(){
     	// 商家接单,首先我需要有查询一个已支付的订单信息,于是在接口还没有进行完成开发之前,使用Mock进行模拟
      String orderId = "20200429143906111";
      Mockito.when(ordersMapper.selectByOrderId(orderId)).then(new Answer<Orders>() {
        @Override
        public Orders answer(InvocationOnMock invocationOnMock) throws Throwable {
          Orders orders = new Orders();
          orders.setId(invocationOnMock.getArgument(0, Integer.class));
          orders.setOrderStatus(OrderStatusEnum.OrderWaitHandle.getValue());   //已支付待受理状态
          return orders;
        }
      });

        // 执行查询并验证查询结果
        Orders orders = ordersMapper.selectByOrderId(orderId);
        Assert.assertEquals(orderId, orders.getOrderId());
        Assert.assertEquals(OrderStatusEnum.OrderWaitHandle.getValue(), orders.getOrderStatus());

        // 满足上述的条件之后，接下来我们需要进行商家接单操作
        orders.setOrderStatus(OrderStatusEnum.OrderHandling.getValue());
        Mockito.when(ordersMapper.confirmOrder(orders)).thenReturn(1);
      
        // 执行接单操作并验证
        int res = ordersMapper.confirmOrder(orders);
        Assert.assertEquals(1, res);
      
        // 根据orderId查询订单验证订单状态
       Mockito.when(ordersMapper.selectByOrderId(orderId)).then(new Answer<Orders>() {
        @Override
        public Orders answer(InvocationOnMock invocationOnMock) throws Throwable {
          Orders orders = new Orders();
          orders.setId(invocationOnMock.getArgument(0, Integer.class));
          orders.setOrderStatus(OrderStatusEnum.OrderHandling.getValue());   //已接单状态
          return orders;
        }
      });
      // 验证订单状态
      Orders orders = ordersMapper.selectByOrderId(orderId);
      Assert.assertEquals(OrderStatusEnum.OrderHandling.getValue(), orders.getOrderStatus());
       
        // 再次执行接单操作,这个时候测试结果应该是失败,已经执行过一次,需要保证幂等
         Mockito.when(ordersMapper.confirmOrder(orders)).thenReturn(0);
         res = ordersMapper.confirmOrder(orders);
         Assert.assertEquals(0, res);
    }
}
```

2. 优化代码并将红灯显示区域修复,运行单元测试用例并促使其出现绿灯,保证当前单元测试是成功的

```java
// 比如上述还没有定义接口OrdersMapper,那么这个时候就需要先定义出OrdersMapper的接口
public interface OrdersMapper extends BaseMapper<Orders>{
   
   Orders selectByOrderId(String orderId);
   int confirmOrder(Orders orders);
}
```

3. 当接口开发完成之后,我们根据上述的单元测试用例注释掉Mock数据,基于Spring的配置进行加载并对接口功能进行单元测试.

4. 最后通过单元测试报告来优化功能代码,驱使一个接单功能的实现能够符合预期的业务需求目标.

更多关于单元测试实践参考如下:

- [Modern Best Practices for Testing in Java](https://phauer.com/2019/modern-best-practices-testing-java/)

- [7-tips-for-writing-better-unit-tests-in-java](https://dzone.com/articles/7-tips-for-writing-better-unit-tests-in-java)

- [Java Unit Testing Best Practices](https://dzone.com/articles/java-unit-testing-best-practices-how-to-get-the-mo)

- [Unit-Level Performance Testing in Java](https://dzone.com/articles/unit-level-performance-testing-in-java)

#### Jenkins构建自动化单元测试

- 单元测试自动化

1. 使用docker-compose的方式对项目进行构建,即docker-compse-build.yaml

```yaml
version: '3'
services:
  product-service-api-ci:
    image: maven:3.5.2-jdk-8-alpine
    volumes:
      - .:/build
    working_dir: /build
    command: bash -c "mvn package && mvn cobertura:cobertura"
```

2. 根据maven的构建的生命周期,在进行maven打包的时候会执行当前项目下的单元测试类,直接通过docker compose up启动构建服务,至此在流水线上就可以看到Jenkins构建单元测试输出结果:

![boathouse-structure-junit-jenkins-test](../images/junit/boathouse-structure-junit-jenkins-test.jpg)

3. Jenkins安装Html Publisher插件输出单元测试报告

打开Jenkins界面,在基于流水线的构建基础下,点击进入左侧的Manage Jenkins(系统配置),找到插件管理并点击进入,点开可选插件面板,搜索html,如下界面:

![boathouse-structure-junit-jenkins-search](../images/junit/boathouse-structure-junit-jenkins-search.jpg)

这个时候点击直接安装即可(上述已经安装过插件).

3. 需要在项目的pom文件进行配置,在上述单元测试的工具已经介绍并贴出相关的配置(Java Code Coverage)

4. 根据上述的pom文件配置以及maven的插件,在执行构建打包的时候会输出Java Code Coverage的测试报告结果.只需要将报告结果export出来,即在JenkinsFile的构建步骤中增加输出报告的结果,即如下:

```bash
## product-service项目使用maven进行构建输出报告目录: ./product-service/api/target/site/jacoco-it
## 在JenkinsFile下的构建build-product-service的步骤最后增加以下命令即可
publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: './product-service/api/target/site/jacoco-it', reportFiles: 'index.html', reportName: 'Junit Report', reportTitles: ''])
```

最后只需要在Jenkins上构建流水线,等待单元测试报告的结果输出即可,如下:

![boathouse-structure-junit-jenkins-report1](../images/junit/boathouse-structure-junit-jenkins-report1.jpg)

然后在当前界面的右上角点击“制品”Tab,显示如下:

![boathouse-structure-junit-jenkins-report2](../images/junit/boathouse-structure-junit-jenkins-report2.jpg)

点击上述的Junit Report即可显示单元测试输出报告,即:

![boathouse-structure-junit-jenkins-report3](../images/junit/boathouse-structure-junit-jenkins-report3.jpg)