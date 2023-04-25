<template>
    <div class="container">
        <div class="login-wrapper">
            <div class="header">Login</div>
            <form action="#" method="post" class="form-wrapper">
                <input type="text" name="username" placeholder="请输入用户名" class="input-item">
                <input type="password" name="password" placeholder="请输入密码" class="input-item">
                <button class="btn" @click.prevent="handlelogin">登录</button>
            </form>
            <div class="msg">
                没有账户?
                <a href="#">注册</a>
            </div>
        </div>
    </div>
</template>

<script setup lang='ts'>
import { useRouter } from 'vue-router';
const router = useRouter();
function handlelogin(): void {
    console.log("1");

    router.push({
        name: 'Index',
    })
}
</script>

<style lang='less' scoped>
.container {
    height: 100%;
    background-image: linear-gradient(to right, #fbc2eb, #a6c1ee);
}

@offset: 50%;

.login-wrapper {
    background-color: #fff;
    width: 358px;
    height: 588px;
    border-radius: 15px;
    padding: 0 50px;
    position: relative;
    left: @offset;
    top: @offset;
    transform: translate(-@offset, -@offset);
}

.header {
    font-size: 38px;
    font-weight: bold;
    text-align: center;
    line-height: 200px;
}

.input-item {
    display: block;
    width: 100%;
    margin-bottom: 20px;
    border: 0;
    padding: 10px;
    border-bottom: 1px solid rgb(128, 125, 125);
    font-size: 15px;
    outline: none;

    &::placeholder {
        text-transform: uppercase;
    }
}


.btn {
    text-align: center;
    width: 100%;
    padding: 10px;
    margin-top: 40px;
    background-image: linear-gradient(to right, #a6c1ee, #fbc2eb);
    color: #fff;
    border: none;
    display: block;
    box-sizing: content-box;
    border-radius: 5px;
}

.msg {
    text-align: center;
    line-height: 88px;
}

a {
    text-decoration-line: none;
    color: #abc1ee;
}
</style>