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
    - `Start MySQL from Mac Preferences > MySQL (I find i have to stop and restart the instance to allow me to connect)`


- Register Slack Apps OAUTH Token as an environment varoable (until we get somthing better sorted out)


- Cake Springboot Application - `./gradlew bootRun`



