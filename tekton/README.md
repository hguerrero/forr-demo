```sh
tkn pipeline start build-and-deploy -w name=shared-workspace,volumeClaimTemplateFile=https://raw.githubusercontent.com/openshift/pipelines-tutorial/pipelines-1.4/01_pipeline/03_persistent_volume_claim.yaml -p deployment-name=pipelines-file-upload -p git-url=https://github.com/hguerrero/forr-demo.git -p IMAGE=image-registry.openshift-image-registry.svc:5000/pipelines/pipelines-file-upload
```