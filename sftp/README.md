# SFTP Install on OpenShift

1. Create new project `sftp`
   
    ```sh
    oc new-project sftp --display-name="Internal sftp server"
    ```
    
1. Allow running as `root` for the project

    ```sh
    oc adm policy add-scc-to-user anyuid system:serviceaccount:sftp:default
    ```

1. Deploy the application from a container image as deployment config

    ```sh
    oc new-app quay.io/redhatintegration/sftp --name ftpserver --as-deployment-config
    ```

1. Create a config map with the users

    ```sh
    oc create secret generic ftp-users --from-file=sftp/users.conf
    ```

1. Create a config map with custom keys

    ```sh
    oc create secret generic sftp-ssh-key --from-file=demo.rsa.key
    ```

1. Mount users file to deployment

    ```sh
    oc set volume dc/ftpserver --add --mount-path=/etc/sftp --secret-name=ftp-users --read-only=true
    ```

1. Mount key file to deployment

    ```sh
    oc set volume dc/ftpserver --add --mount-path=/home/foo/.ssh/keys --secret-name=sftp-ssh-key --read-only=true
    ```
