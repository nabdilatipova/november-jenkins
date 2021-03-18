node {
	properties([pipelineTriggers([cron('H/5 * * * *')])])
	stage("Stage1"){
		git 'https://github.com/farrukh90/packer.git'
}
	stage("Stage2"){
		echo "hello"
}
	stage("Stage3"){
		echo "hello"
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
}
