# violation-builder
A small java library for building violations.

[![Build Status](https://travis-ci.org/thiagogarbazza/violation-builder.svg?branch=master)](https://travis-ci.org/thiagogarbazza/violation-builder)
[![Coverage Status](https://sonarcloud.io/api/project_badges/measure?project=com.github.thiagogarbazza:violation-builder&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.github.thiagogarbazza:violation-builder)
[![Coverage Status](https://sonarcloud.io/api/project_badges/measure?project=com.github.thiagogarbazza:violation-builder&metric=coverage)](https://sonarcloud.io/dashboard?id=com.github.thiagogarbazza:violation-builder)

## Usage samples


```java
void example() throws ViolationException {
  boolean condition = true;

  new ViolationBuilder()
    .error(condition, "error-key-01", "some error-01 message.")
    .error("error-key-02", "some error-02 message.")
    .build();
}
```

```java
void example() throws ViolationException {
  boolean condition = true;
  
  new ViolationBuilder()
    .warning(condition, "error-key-01", "some error-01 message.")
    .warning("error-key-02", "some error-02 message.")
    .build();
}
```

```java
void example() throws ViolationException {
  boolean condition = true;

  new ViolationBuilder()
    .error(condition, "error-key", () -> "some error message.")
    .warning(condition, "waring-key", () -> "some warning message.")
    .buildIgnoreWarnings();
}
```

## Installing

Available in the [Maven Central repository].

Add a dependency to `com.github.thiagogarbazza:violation-builder` in your project.

Example using maven:
```xml
<dependency>
  <groupId>com.github.thiagogarbazza</groupId>
  <artifactId>violation-builder</artifactId>
  <version>1.0.0</version>
</dependency>
```

## Built With

- [Java](https://www.java.com/) - The lang used
- [Junit](https://junit.org/junit5/) - The framework test used
- [Maven](https://maven.apache.org/) - Dependency Management

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository].

## Authors

- **[Thiago Garbazza](https://github.com/thiagogarbazza)** - *Initial work*

See also the list of [contributors] who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

## Acknowledgments

- Java 7+
- Junit jupter


[contributors]: (https://github.com/thiagogarbazza/violation-builder/contributors)
[tags on this repository]: https://github.com/thiagogarbazza/violation-builder/tags

[Maven Central repository]: http://mvnrepository.com/artifact/com.github.thiagogarbazza/violation-builder
