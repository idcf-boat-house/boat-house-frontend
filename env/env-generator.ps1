# === Genernal Note ===
# Template specific tool invoking script
# Should execute before running the normal environment scripts

# === Template Specific Note ===
# Template Name: ls116-k8s-vmss-china
# Template Description: this template is using a customized version of acs-engine (acs-engine-lsx.exe) to gerneate 
# Resource Manager Template files (labs-azuredeploy.json and labs-azuredeploy.parameters.json) back into ..\labs folder

Param(
   [string] [Parameter(Mandatory=$true)] $AzureUserName,
   [string] [Parameter(Mandatory=$true)] $AzureUserPwd,
   [string] [Parameter(Mandatory=$true)] $SubscriptionName,
   [string] [Parameter(Mandatory=$true)] $AzureSPApplicationId,
   [string] [Parameter(Mandatory=$true)] $AzureSPApplicationKey
)


function New-SWRandomPassword {
    <#
    .Synopsis
       Generates one or more complex passwords designed to fulfill the requirements for Active Directory
    .DESCRIPTION
       Generates one or more complex passwords designed to fulfill the requirements for Active Directory
    .EXAMPLE
       New-SWRandomPassword
       C&3SX6Kn

       Will generate one password with a length between 8  and 12 chars.
    .EXAMPLE
       New-SWRandomPassword -MinPasswordLength 8 -MaxPasswordLength 12 -Count 4
       7d&5cnaB
       !Bh776T"Fw
       9"C"RxKcY
       %mtM7#9LQ9h

       Will generate four passwords, each with a length of between 8 and 12 chars.
    .EXAMPLE
       New-SWRandomPassword -InputStrings abc, ABC, 123 -PasswordLength 4
       3ABa

       Generates a password with a length of 4 containing atleast one char from each InputString
    .EXAMPLE
       New-SWRandomPassword -InputStrings abc, ABC, 123 -PasswordLength 4 -FirstChar abcdefghijkmnpqrstuvwxyzABCEFGHJKLMNPQRSTUVWXYZ
       3ABa

       Generates a password with a length of 4 containing atleast one char from each InputString that will start with a letter from 
       the string specified with the parameter FirstChar
    .OUTPUTS
       [String]
    .NOTES
       Written by Simon Wåhlin, blog.simonw.se
       I take no responsibility for any issues caused by this script.
    .FUNCTIONALITY
       Generates random passwords
    .LINK
       http://blog.simonw.se/powershell-generating-random-password-for-active-directory/
   
    #>
    [CmdletBinding(DefaultParameterSetName='FixedLength',ConfirmImpact='None')]
    [OutputType([String])]
    Param
    (
        # Specifies minimum password length
        [Parameter(Mandatory=$false,
                   ParameterSetName='RandomLength')]
        [ValidateScript({$_ -gt 0})]
        [Alias('Min')] 
        [int]$MinPasswordLength = 8,
        
        # Specifies maximum password length
        [Parameter(Mandatory=$false,
                   ParameterSetName='RandomLength')]
        [ValidateScript({
                if($_ -ge $MinPasswordLength){$true}
                else{Throw 'Max value cannot be lesser than min value.'}})]
        [Alias('Max')]
        [int]$MaxPasswordLength = 12,

        # Specifies a fixed password length
        [Parameter(Mandatory=$false,
                   ParameterSetName='FixedLength')]
        [ValidateRange(1,2147483647)]
        [int]$PasswordLength = 8,
        
        # Specifies an array of strings containing charactergroups from which the password will be generated.
        # At least one char from each group (string) will be used.
        [String[]]$InputStrings = @('abcdefghijkmnpqrstuvwxyz', 'ABCEFGHJKLMNPQRSTUVWXYZ', '23456789', '!"#%&'),

        # Specifies a string containing a character group from which the first character in the password will be generated.
        # Useful for systems which requires first char in password to be alphabetic.
        [String] $FirstChar,
        
        # Specifies number of passwords to generate.
        [ValidateRange(1,2147483647)]
        [int]$Count = 1
    )
    Begin {
        Function Get-Seed{
            # Generate a seed for randomization
            $RandomBytes = New-Object -TypeName 'System.Byte[]' 4
            $Random = New-Object -TypeName 'System.Security.Cryptography.RNGCryptoServiceProvider'
            $Random.GetBytes($RandomBytes)
            [BitConverter]::ToUInt32($RandomBytes, 0)
        }
    }
    Process {
        For($iteration = 1;$iteration -le $Count; $iteration++){
            $Password = @{}
            # Create char arrays containing groups of possible chars
            [char[][]]$CharGroups = $InputStrings

            # Create char array containing all chars
            $AllChars = $CharGroups | ForEach-Object {[Char[]]$_}

            # Set password length
            if($PSCmdlet.ParameterSetName -eq 'RandomLength')
            {
                if($MinPasswordLength -eq $MaxPasswordLength) {
                    # If password length is set, use set length
                    $PasswordLength = $MinPasswordLength
                }
                else {
                    # Otherwise randomize password length
                    $PasswordLength = ((Get-Seed) % ($MaxPasswordLength + 1 - $MinPasswordLength)) + $MinPasswordLength
                }
            }

            # If FirstChar is defined, randomize first char in password from that string.
            if($PSBoundParameters.ContainsKey('FirstChar')){
                $Password.Add(0,$FirstChar[((Get-Seed) % $FirstChar.Length)])
            }
            # Randomize one char from each group
            Foreach($Group in $CharGroups) {
                if($Password.Count -lt $PasswordLength) {
                    $Index = Get-Seed
                    While ($Password.ContainsKey($Index)){
                        $Index = Get-Seed                        
                    }
                    $Password.Add($Index,$Group[((Get-Seed) % $Group.Count)])
                }
            }

            # Fill out with chars from $AllChars
            for($i=$Password.Count;$i -lt $PasswordLength;$i++) {
                $Index = Get-Seed
                While ($Password.ContainsKey($Index)){
                    $Index = Get-Seed                        
                }
                $Password.Add($Index,$AllChars[((Get-Seed) % $AllChars.Count)])
            }
            Write-Output -InputObject $(-join ($Password.GetEnumerator() | Sort-Object -Property Name | Select-Object -ExpandProperty Value))
        }
    }
}


#generate 8 length random password
$StagingDirectory = Split-Path -Parent $MyInvocation.MyCommand.Definition
"staging directory = $StagingDirectory"
$toolConfigFilePath= $StagingDirectory + '\k8s\template-tool-config.json'

$toolConfigFileContent = Get-Content $toolConfigFilePath | Out-String 

#generate dnsPrefix and adminPassword and replace

$randomString=New-SWRandomPassword -InputStrings abcdefghijkmnpqrstuvwxyz -PasswordLength 8 -FirstChar abcdefghijkmnpqrstuvwxyz;
$k8srandomString = "k8s-$randomString";
"K8s DNS is $k8srandomString"
$toolConfigFileContent=$toolConfigFileContent.Replace("%{dnsPrefix}%", $k8srandomString);
$ResourceGroupName = "icdps-$randomString-rg";
#replace destApplicationId and destApplicationKey
$toolConfigFileContent=$toolConfigFileContent.Replace("%{destApplicationId}%", $AzureSPApplicationId);
$toolConfigFileContent=$toolConfigFileContent.Replace("%{destApplicationKey}%", $AzureSPApplicationKey);

#generate ssh key and replace
$tempFolder = $StagingDirectory + '\k8s\temp';
$sshFolder = $tempFolder + '\ssh'
$existing = [System.Boolean](Test-Path $tempFolder)
if($existing)
{
    Remove-Item $tempFolder -recurse
}
New-Item $sshFolder -type directory
$Email = "info@leasoftx.com"
$sshKeyFileName = $sshFolder + "\id_rsa"
& 'C:\Program Files\Git\usr\bin\ssh-keygen.exe'  -t rsa -C $Email -f $sshKeyFileName -P """"

$sshkeyPubFileName = $sshKeyFileName+".pub"

# remove any ending new line charactors
$publicKeyContent = ([IO.File]::ReadAllText($sshkeyPubFileName)).TrimEnd(([char]0x2424, [char]0x23CE, [char]0x240D, [char]0x240A)).replace("`n","").replace("`r","")
$privateKeyContent = [IO.File]::ReadAllText($sshKeyFileName).TrimEnd(([char]0x2424, [char]0x23CE, [char]0x240D, [char]0x240A)).replace("`n","").replace("`r","")
Write-Host " publick key : $publicKeyContent"
Write-Host " private key : $privateKeyContent"

$toolConfigFileContent=$toolConfigFileContent.Replace("%{sshRSAPublicKey}%", $publicKeyContent);

# Save the replace file
"Writing k8s configuration to: $toolConfigFileContent"

# Use UTF-8 Encoding to ensure other script can read from this file, BOM is disabled
$utf8Bom = New-Object System.Text.UTF8Encoding $false
[System.IO.File]::WriteAllLines($toolConfigFilePath, $toolConfigFileContent, $utf8Bom)

#
# Use acs-engine to gereate Azure Resource Manager Template
# Make sure acs-engine-lsx.exe is located in c:\tools\acs-engine folder
# Source code from https://github.com/ups216/acs-engine
#
"Running template specific tool ... "
$toolCmd = $StagingDirectory + "\k8s\acs-engin\acs-engine.exe generate '$toolConfigFilePath' -o '$tempFolder'"
Write-Host "Running Template specific tool command:`n $toolCmd"
Invoke-Expression $toolCmd

az login -u $AzureUserName -p $AzureUserPwd
az account set --subscription $SubscriptionName
az group create --name $ResourceGroupName --location "Southeast Asia"

$K8sDeployTemplate= $StagingDirectory + '\k8s\temp\azuredeploy.json'
$K8sDeployParamsTemplate= $StagingDirectory + '\k8s\temp\azuredeploy.parameters.json'

$LinuxDeployTemplate= $StagingDirectory + '\linux\labs-azuredeploy.json'
$LinuxDeployParamsTemplate= $StagingDirectory + '\linux\labs-azuredeploy.parameters.json'
$LinuxDeployParamsContent = Get-Content $LinuxDeployParamsTemplate | Out-String 
$linuxRandomString = "linux-$randomString";
$LinuxDeployParamsContent=$LinuxDeployParamsContent.Replace("%{adminPassword}%", $AzureUserPwd);
$LinuxDeployParamsContent=$LinuxDeployParamsContent.Replace("%{dnsLabelPrefix}%", $linuxRandomString);
"Writing linux configuration to: $LinuxDeployParamsContent"
[System.IO.File]::WriteAllLines($LinuxDeployParamsTemplate, $LinuxDeployParamsContent, $utf8Bom)

"Start create linux vm..."
az group deployment create --resource-group $ResourceGroupName --template-file $LinuxDeployTemplate --parameters $LinuxDeployParamsTemplate

"Start create k8s vmss..."
az group deployment create --resource-group $ResourceGroupName --template-file $K8sDeployTemplate --parameters $K8sDeployParamsTemplate