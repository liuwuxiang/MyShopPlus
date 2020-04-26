<template>
  <div class="components-container">
    <pan-thumb :image="image" />

    <el-button type="primary" icon="el-icon-upload" style="position: absolute;bottom: 15px;margin-left: 40px;" @click="toggleShow">
      修改头像
    </el-button>

    <image-cropper
               field="multipartFile"
               @crop-success="cropSuccess"
               @crop-upload-success="cropUploadSuccess"
               @crop-upload-fail="cropUploadFail"
               v-model="show"
               :width="300"
               :height="300"
               :url="url"
               :params="params"
               :headers="headers"
               img-format="png"/>

  </div>
</template>

<script>

    import PanThumb from '@/components/PanThumb'
    import ImageCropper from 'vue-image-crop-upload';
    import {getToken} from "@/utils/auth";
    import {modifyIcon, update} from '@/api/profile'

    export default {
        name: "ProfileIcon",
        components: { ImageCropper, PanThumb },
        data(){
          return{
              url: process.env.VUE_APP_BASE_API + 'upload',
              show: false,
              image: this.$store.getters.avatar,
              params: {
                access_token: getToken(),
              },
              headers: {
                smail: '*_~'
              },
          }
        },
        methods: {
          toggleShow() {
            this.show = !this.show;
          },

          /**
           * crop success
           *
           * [param] imgDataUrl
           * [param] field
           */
          cropSuccess(image, field){
            console.log('-------- crop success --------');
            this.image = image;
          },

          /**
           * upload success
           *
           * [param] jsonData   服务器返回数据，已进行json转码
           * [param] field
           */
          cropUploadSuccess(jsonData, field){
            console.log('-------- upload success --------');
            console.log(jsonData);
            console.log('field: ' + field);

            //头像上传成功直接修改
            modifyIcon({
              username: this.$store.getters.name,
              path: jsonData.data.path
            }).then(response => {
                this.$message({
                  message: response.message,
                  type: 'success'
                })

                //设置vuex里的头像
                this.$store.dispatch('user/setAvatar', jsonData.data.path)
            }).catch(() => {
              this.formLoading = false
            })
          },

          /**
           * upload fail
           *
           * [param] status    server api return error status, like 500
           * [param] field
           */
          cropUploadFail(status, field){
            console.log('-------- upload fail --------');
            console.log(status);
            console.log('field: ' + field);
          }
        }
    }
</script>

<style scoped>

</style>
