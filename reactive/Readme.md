### WebFlux

Spring Boot testing:
- unit
- contract
- integration
- e2e


- @SpringBootTest - loads a full application context;
- @WebMvcTest, @DataJpaTest, @JsonTest - "sliced" integration test, loads only a minimal application context, relevant to a specific layer;
- @TestRestTemplate and @WebTestClient - make HTTP requests in integration tests;
  - @TestRestTemplate is for blocking calls;
  - @WebTestClient is for reactive applications;
- @AutoConfigureTestDatabase, @AutoConfigureMockMvc, @AutoConfigureDataJpa, @BeforeEach, @AfterEach, @TestConfiguration,
  @Import(MyTestConfiguration.class), @ConfigurationProperties(prefix = "my"), @ActiveProfiles("test");
- @MockitoBean and @MockitoSpyBean:
  - @MockitoBean replaces the bean entirely;
  - @MockitoSpyBean keeps the original bean's functionality, except for where you explicitly stub or mock a method;
