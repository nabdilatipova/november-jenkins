node {
	properties([pipelineTriggers([cron('* * * * *')])])
	stage("Stage1"){
		echo "hello"
}
	stage("Stage2"){
		echo "hello"
}
	stage("Stage3"){
		echo "hello"
}
	stage("Stage4"){
		echo "hello"
}
	stage("Send Email to Support"){
		mail bcc: '', body: 'Running', cc: 'support@company.com', from: '', replyTo: '', subject: 'Test', to: 'farrukhsadykov@gmail.com'
	}
    stage("Send Notifications to Slack"){
		slackSend color: '#BADA55', message: 'Hello, World!'
	}
}
