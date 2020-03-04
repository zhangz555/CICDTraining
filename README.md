## Training Instructions 

### Step 0/9 Source Code

``` bash
git clone -b source_code --single-branch https://github.com/EvgeniaPatsoni/OpenWIS_Training.git 
```

### Step 1/9 Application Maven Build

``` bash
mvnw.cmd clean install -Dmaven.test.skip 
``` 

### Step 2/9 Junit Tests Execution

``` bash
mvnw.cmd verify 
```


### Step 3/9 Application Deployment 

``` bash
java -jar target/cicd-example-1.0.0-SNAPSHOT.jar 
```

### Step 4/9 GitHub Hosting

``` bash
git init
git remote add origin <repo_url>
git add .
git commit –m "<commit_message>"
git push --set-upstream origin master
```

### Step 5/9 Travis-CI Integration

```xml
language: java

jdk: openjdk11

before_install:
	- chmod +x mvnw

script:
	- ./mvnw clean install
```

```xml
notifications:
 	email:
		recipients:
		- <email_address>
	on_success: always
	on_failure: always
```

### Step 6/9 SonarCloud Integration
```xml
addons:
	sonarcloud:
		organization: "<organization_name>"
		token:
			secure: "**************************"

script:
	- ./mvnw clean install sonar:sonar -Dsonar.projectKey=<key>
```

### Step 7/9 OWASP Maven Dependency Check
``` bash
mvnw.cmd org.owasp:dependency-check-maven:aggregate
```

### Step 8/9 Local Sonar Analysis Execution
``` bash
mvnw.cmd sonar:sonar -Dsonar.host.url=https://sonarcloud.io -Dsonar.organization=<organization_name> -Dsonar.projectKey=<key> -Dsonar.login=<token> 
```

### Step 9/9 Remote SSH Deployment
```xml
before_install:
	- openssl aes-256-cbc -K $encrypted_180f41e11b0e_key -iv $encrypted_180f41e11b0e_iv
	 -in <key_name>.enc -out <key_name> –d
	- chmod +x mvnw
	- eval "$(ssh-agent -s)"
	- chmod 600 <key_name>
	- ssh-add <key_name>
	- echo -e "Host ${DEPLOY_HOST}\n\tStrictHostKeyChecking no\n" >> ~/.ssh/config

deploy:
	- provider: script
	  skip-cleanup: true
	  script: rsync -r --quiet --delete-after ${TRAVIS_BUILD_DIR}/target/cicd-example-*.jar ${DEPLOY_USER}@${DEPLOY_HOST}:${DEPLOY_DIRECTORY}    
	  on:
	  	branch: master

	- provider: script
	  skip-cleanup: true
	  script: sh ${TRAVIS_BUILD_DIR}/remote_deploy.sh
	  on:
	  	branch: master
```