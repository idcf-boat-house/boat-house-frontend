Param(
   [string] [Parameter(Mandatory=$true)] $AzureUserName,
   [string] [Parameter(Mandatory=$true)] $AzureUserPwd,
   [string] [Parameter(Mandatory=$true)] $SubscriptionName
)

az login -u $AzureUserName -p $AzureUserPwd
az account set --subscription $SubscriptionName
az ad app create --display-name icdps-test --identifier-uris https://mytestapp.websites.net
az role assignment create --assignee icdps-test --role Owner
# $newsec = ConvertTo-SecureString -String "/a4oq:Gj[/LzfMLT9gA9q3[x/yJ2YNUW" -AsPlainText -Force
# $cred = New-Object -TypeName System.Management.Automation.PSCredential("9ea170b4-57ff-4cc3-a920-c943ed9c26ef", $newsec)
# Login-AzureRmAccount -Credential $cred -TenantId "248ba678-61fd-4ce3-90f9-9881212469b5" -ServicePrincipal
# Set-AzureRmContext -SubscriptionID "a7daad14-c394-4df7-a33f-c8133c5ab708"
# # New-AzureRmResourceGroup -Location "Southeast Asia" -Name "k8sTestRG" -Force

# New-AzureRmResourceGroupDeployment -Name icdps-k8s-2020020601 `
#                                        -ResourceGroupName "k8sTestRG" `
#                                        -TemplateFile "C:\WilsonBoData\tmp\gits\icdps\icdps-general\env\k8s\arm\azuredeploy.json" `
#                                        -TemplateParameterFile "C:\WilsonBoData\tmp\gits\icdps\icdps-general\env\k8s\arm\params.json" `
#                                        -Force -Verbose 