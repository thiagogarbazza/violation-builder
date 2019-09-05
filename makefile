.RECIPEPREFIX +=
.DEFAULT_GOAL := clean

clean:
  mvn clean

quality-check:
  mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent verify sonar:sonar

deploy:
  mvn deploy --settings src/config/ci/maven-settings.xml

release-prepare:
  mvn -B release:clean release:prepare
