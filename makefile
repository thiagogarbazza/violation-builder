.RECIPEPREFIX +=
.DEFAULT_GOAL := clean

clean:
  mvn clean

quality-check:
  mvn org.jacoco:jacoco-maven-plugin:prepare-agent org.jacoco:jacoco-maven-plugin:prepare-agent-integration verify org.jacoco:jacoco-maven-plugin:report

deploy:
  mvn deploy --settings src/config/ci/maven-settings.xml

release-prepare:
  mvn -B release:clean release:prepare
