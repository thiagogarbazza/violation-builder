# violation-builder
A small java library for building violations.

[![Build Status](https://travis-ci.org/thiagogarbazza/violation-builder.svg?branch=master)](https://travis-ci.org/thiagogarbazza/violation-builder)
[![Coverage Status](https://sonarcloud.io/api/project_badges/measure?project=com.github.thiagogarbazza:violation-builder&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.github.thiagogarbazza:violation-builder)
[![Coverage Status](https://sonarcloud.io/api/project_badges/measure?project=com.github.thiagogarbazza:violation-builder&metric=coverage)](https://sonarcloud.io/dashboard?id=com.github.thiagogarbazza:violation-builder)


## Install

Available in the [Maven Central repository].

Add a dependency to `com.github.thiagogarbazza:violation-builder` in your project.

Example using maven:
```xml
<dependency>
  <groupId>com.github.thiagogarbazza</groupId>
  <artifactId>violation-builder</artifactId>
  <version>1.0.0-SNAPSHOT</version>
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

[Maven Central repository]: http://mvnrepository.com/artifact/com.github.thiagogarbazza/violation-builder
