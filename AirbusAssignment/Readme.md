#### Deploy Elastic Stack using minikube by following steps

Note: Testing done on (macos)

Pre-requisite:

System should have
1)kubectl

2)docker 

3)minikube 

* brew update && brew install kubectl && brew cask install docker && brew install minikube

* minikube start

* kubectl get nodes

To open the dashboard : minikube dashboard

#### To create the services:
kubectl apply -f elasticstack/elasticsearch.yml

kubectl apply -f elasticstack/kibana.yml,

#### To verify the pods are ready

kubectl get pods --all-namespaces,

kubectl describe pods <pod-name>

### if all pods are ready

you can open kibana dashboard

http://localhost:5601/

### To get jenkins container

* docker run -d --name Jenkins -p 8080:8080 -p 50000:50000 -v jenkins_data:/var/jenkins_home jenkins/jenkins:lts
* docker logs <container id>
* Get the password token
* Open localhost:8080 and enter the password
* Select the suggested plugins
* Create user account
* Give host machine IP address in Jenkins url
* Manage Jenkins
* Install plugins Kebernetes and kubernetes contineous deployment
* Configure system and set the credential option i did with .kube config file.
* create the jenkins pipeline and provide the configuration details
* set the jenkins with git repository and branch
* provide the path of the jenkins file which is having all the steps to deploy the services.

### Configure webHook To trigger the jenkins job automatically on any new commit in github

1) Go to git repository and click on settings
2) Add the webhook, by providing the payload url as jenkins url and content-type as application/json









