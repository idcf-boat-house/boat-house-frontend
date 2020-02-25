az login -u wilsonbo@leansoftx.com -p Croco321!
az account set --subscription <subscription name or id>
az group create --name ICDPS-ESA-K8S-RG --location "Southeast Asia"
az group deployment create --resource-group ICDPS-ESA-K8S-RG --template-file "arm\azuredeploy.json" --parameters "C:\WilsonBoData\tmp\gits\icdps\icdps-general\env\k8s\arm\params.json"