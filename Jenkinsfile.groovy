properties([
    buildDiscarder(logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '10', numToKeepStr: '10')), 
    parameters([choice(choices: [
        'dev', 
        'qa', 
        'stage', 
        'prod'], 
    description: 'Which Environment to Build? ', name: 'ENVIRONMENT_TO_BUILD')]), 
    pipelineTriggers([cron('H/5 * * * *')])])


node {
	stage("Stage1"){
		checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/farrukh90/infrastructure.git']]])
}
	stage("Call Another Job"){
		build "Packer"
}
	stage("Stage3"){
		timestamps {
            echo "hello"
    }
}
	stage("Stage4"){
		timestamps {
            echo "hello"
        }
}
	stage("Script"){
		sh label: '', script: 
		'''#!/bin/bash
			if [ ! -d /tmp/foo.txt ]; 
			then
				echo "Folder not found!"
				echo "Creating a folder"
				mkdir -p "/tmp/foo.txt" 
			fi
		'''
	}
	stage("Send Email to Support"){
		mail bcc: '', body: 'Running', cc: 'support@company.com', from: '', replyTo: '', subject: 'Test', to: 'farrukhsadykov@gmail.com'
	}
    stage("Send Notifications to Slack"){
		slackSend color: '#BADA55', message: 'Hello, World!'
	}
    // stage("Sleep"){
	// 	sleep 60000000
    // }
    // stage("Intentionally Failed"){
    // 		error 'failed'
    // }
}
