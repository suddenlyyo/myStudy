# 不明白的代码demo

## Java 项目中包 domain、entity、model 的区别

Entity
一般情况下此结构与数据库结构一一对应，作为程序与数据库的映射桥梁。

Model
针对某一个业务需求，组合或是过滤一组数据。

Domain
一般是一组数据组合，包含某一个大业务逻辑的所有数据集合。

ps：model 和 domain 概念比较重合，都是对业务的数据抽象，主要区别是 model 更多用在传统的MVC结构中，domain
更多的使用在微服务架构或是DDD原则设计的项目中。也可以按照domain比model范围更大的角度来理解。实际使用中，如果项目不是特别大，可以model或domain二选一。

## junit4和junit5 对比

| 特征               | JUNIT 4        | JUNIT 5        |
|------------------|----------------|----------------|
| 声明一种测试方法         | `@Test`        | `@Test`        |
| 在当前类中的所有测试方法之前执行 | `@BeforeClass` | `@BeforeAll`   |
| 在当前类中的所有测试方法之后执行 | `@AfterClass`  | `@AfterAll`    |
| 在每个测试方法之前执行      | `@Before`      | `@BeforeEach`  |
| 每种测试方法后执行        | `@After`       | `@AfterEach`   |
| 禁用测试方法/类         | `@Ignore`      | `@Disabled`    |
| 测试工厂进行动态测试       | NA             | `@TestFactory` |
| 嵌套测试             | NA             | `@Nested`      |
| 标记和过滤            | `@Category`    | `@Tag`         |
| 注册自定义扩展          | NA             | `@ExtendWith`  |

## 注解式事务

在以下情况下，注解式事务可能会失效：

1. 当一个未被捕获的异常抛出时，事务将自动回滚。但是，如果异常被捕获并在try-catch块中处理，那么事务将不会回滚。
2. 当使用@Transactional注解的方法被另一个没有使用@Transactional注解的方法调用时，事务将失效。
3. 当在同一类中调用带有@Transactional注解和不带有@Transactional注解的方法时，事务将失效。
4. 当使用PROPAGATION\_REQUIRES\_NEW事务传播类型时，在嵌套事务内部调用外部事务也会导致事务失效。
5. 当使用基于AspectJ的声明式事务而不是基于Spring AOP的声明式事务时，事务可能会失效。

原因：

1. 当异常被捕获并处理时，相当于告诉事务管理器不需要回滚，因此事务不会回滚。
2. 未使用@Transactional注解的方法没有启动事务，因此调用它的方法也无法使用事务。
3. 在同一个类中调用带有和不带有@Transactional注解的方法，Spring将不知道哪个方法应该启动或参与事务，从而导致事务失效。
4. 使用PROPAGATION\_REQUIRES\_NEW事务传播类型时，每个方法都会启动自己的事务，内部事务的提交或回滚不会影响外部事务，因此事务失效。
5. 基于AspectJ的声明式事务是通过编译时织入实现的，而不是运行时代理。如果应用程序中的类没有被编译时织入，则事务可能会失效。

### 事务传播类型

java 中的事务传播类型包括：

1. REQUIRED：如果当前存在事务，则加入该事务；否则创建一个新的事务。
   . SUPPORTS：支持当前事务，如果当前没有事务，则不使用事务。
2. MANDATORY：强制要求当前存在事务，如果不存在，则抛出异常。
3. REQUIRES\_NEW：创建一个新的事务，并挂起当前事务（如果存在）。
4. NOT\_SUPPORTED：以非事务方式执行操作，如果当前存在事务，则挂起该事务。
5. NEVER：以非事务方式执行操作，如果当前存在事务，则抛出异常。
6. NESTED：如果当前存在事务，则在嵌套事务内执行；否则和REQUIRED一样。

### AspectJ通知类型

AspectJ Java有以下通知类型：

1. Before：在目标方法执行之前执行通知。
2. After：在目标方法执行之后执行通知，无论目标方法是否抛出异常。
3. AfterReturning：在目标方法正常返回之后执行通知。
4. AfterThrowing：在目标方法抛出异常之后执行通知。
5. Around：包围目标方法的通知，可以在目标方法执行前后进行操作。

这些通知类型可用于不同的场景，例如：

1. Before和After通知类型可用于日志记录、权限控制等方面。
2. AfterReturning和AfterThrowing通知类型可用于事务管理、异常处理等方面。
3. Around通知类型可用于性能监测、缓存管理等方面。

它们的作用是在目标方法执行前、执行后或者异常抛出时，插入一段额外的代码逻辑。它们可以与其他 Java 框架结合使用，如 Spring AOP
和 Hibernate

## 编程式事务示例

```Java

@Service
public class UserService {

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private UserMapper userMapper;

    public void createUser(User user) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try {
                    userMapper.insert(user);
                } catch (Exception e) {
                    status.setRollbackOnly();
                    throw new RuntimeException("Failed to create user", e);
                }
            }
        });
    }
}

```
```text
AIO、BIO和NIO是三种不同的输入/输出（I/O）模型，它们在实现方式和性能上有一些区别。

BIO：这是同步且阻塞的通信模式，使用比较传统，模式简单，但并发处理能力低，通信耗时，并依赖网速。在一个连接请求到达服务器时，需要启动一个线程进行处理，直到IO操作完成，用户进程才能运行。如果在这个连接过程中没有任何事情发生，会导致不必要的线程开销。
NIO：这是一种非阻塞同步的通信模式。线程发起io请求后，立即返回（非阻塞io）。用户线程不阻塞等待，但是需要定时轮询检查数据是否就绪。当数据就绪后，用户线程将数据从用户空间写入socket空间，或从socket空间读取数据到用户空间（同步）。由于不停地轮询，会造成不必要的CPU资源的浪费。
AIO：这是异步非阻塞的IO。客户端的I/O请求都是由OS先完成了再通知服务器启动一个线程进行处理。在此之前，需要了解一下同步和异步，阻塞与非阻塞。阻塞与非阻塞是针对进程在访问数据的时候，根据IO操作的就绪状态采取的不同方式。阻塞方式下IO函数将一直等待；非阻塞方式下，IO函数会立即返回一个状态值。
```
