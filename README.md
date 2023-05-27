# 不明白的代码demo
## junit4和junit5 对比
| 特征                             | JUNIT 4            | JUNIT 5            |
| ---------------------------------- | -------------------- | -------------------- |
| 声明一种测试方法                 | `@Test`        | `@Test`        |
| 在当前类中的所有测试方法之前执行 | `@BeforeClass` | `@BeforeAll`   |
| 在当前类中的所有测试方法之后执行 | `@AfterClass`  | `@AfterAll`    |
| 在每个测试方法之前执行           | `@Before`      | `@BeforeEach`  |
| 每种测试方法后执行               | `@After`       | `@AfterEach`   |
| 禁用测试方法/类                  | `@Ignore`      | `@Disabled`    |
| 测试工厂进行动态测试             | NA                 | `@TestFactory` |
| 嵌套测试                         | NA                 | `@Nested`      |
| 标记和过滤                       | `@Category`    | `@Tag`         |
| 注册自定义扩展                   | NA                 | `@ExtendWith`  |