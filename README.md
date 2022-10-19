# cake

### **Overview**

Cake is a pet project for me (mike.smith@xdesign) to help with upskilling myslef in the latest java developments.

### **Architecture Running Locally**

Slack

/\

Slack App (OAUTH Token given)

(slash command etc registered with ngrok -

https://663d-84-13-121-114.eu.ngrok.io/slack/events)

/\

NGROK Running Locally on port 3000

/\

Springboot Application running Bolt on port 3000

### **Useful Links**

Slack API App Console - https://api.slack.com/apps

### To Run

- Ngrok -  `ngrok http 3000`


- MySQL DB

`Start MySQL from Mac Preferences > MySQL (I find i have to stop and restart the instance to allow me to connect)`

To generate some random cake data hit the follwing url - this will pull the number of cakes form the file '
randomwords.txt'

`/admin/cake/magiccreate/{numberOfCakes}`

- Register Slack Apps OAUTH Token as an environment variable (until we get somthing better sorted out)

`export SLACK_OAUTH_TOKEN=<slack oauth token>`

- And the signing secret and bot token - you cna get these from the apps console

`export SLACK_SIGNING_SECRET=<slack app signing secret>`

`export SLACK_BOT_TOKEN=<slack bot token>`

`echo $SLACK_OAUTH_TOKEN` (check its set properly)

- Cake Springboot Application - (in the same terminal where you set the slack oauth token env variable)

`./gradlew bootRun`

### Testing

You can run a couple of tests now to check this is working.

Add the cake app to your slack instance and click onto the Home tab - you should get a description of the App.

Go to the 'caketest' slack channel and type -

`/sendrandomcake mike`

This should respond with a random cake from the database (don't omit the parameter mike as it will nullpointer at the
moment)



