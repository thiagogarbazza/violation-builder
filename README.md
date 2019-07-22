# validation-builder
A small java library for building validations.

[![Build Status](https://travis-ci.org/thiagogarbazza/validation-builder.svg?branch=master)](https://travis-ci.org/thiagogarbazza/validation-builder)
[![Coverage Status](https://sonarcloud.io/api/project_badges/measure?project=com.github.thiagogarbazza:validation-builder&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.github.thiagogarbazza:validation-builder)
[![Coverage Status](https://sonarcloud.io/api/project_badges/measure?project=com.github.thiagogarbazza:validation-builder&metric=coverage)](https://sonarcloud.io/dashboard?id=com.github.thiagogarbazza:validation-builder)


## Install

Available in the [Maven Central repository].

Add a dependency to `com.github.thiagogarbazza:validation-builder` in your project.

Example using maven:
```xml
<dependency>
  <groupId>com.github.thiagogarbazza</groupId>
  <artifactId>validation-builder</artifactId>
  <version>1.0.0</version>
</dependency>
```

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

[Maven Central repository]: http://mvnrepository.com/artifact/com.github.thiagogarbazza/validation-builder
