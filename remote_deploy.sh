#!/usr/bin/env bash

set -xe

ssh -o StrictHostKeyChecking=no "${DEPLOY_USER}"@"${DEPLOY_HOST}" << EOF
echo "#################################################################################################################"
	echo "Stop Spring Boot application execution";
    pkill -9 -f cicd-example*
	echo "Re-deploy Spring Boot application";
	nohup java -jar ${DEPLOY_DIRECTORY}/cicd-example-1.0.0-SNAPSHOT.jar &>/dev/null &
echo "#################################################################################################################"
EOF
