#!/bin/bash
#set -x
#******************************************************************************
# @file    : wait-for-it.sh
# desc     : 控制服务的启动顺序脚本
# 这个脚本有 2 个参数, -d 需要等待的服务和端口, -c 等待的服务和端口启动之后, 自己的启动命令
#******************************************************************************

# 默认两秒后重试
: ${SLEEP_SECOND:=2}

wait_for() {
    echo Waiting for $1 to listen on $2...
    # nc命令用telnet协议测试端口
    while ! nc -z $1 $2;
    do echo waiting...; sleep $SLEEP_SECOND; done
}

# declare 声明 shell 变量
declare DEPENDS
declare CMD

# 每当执行循环时，getopts 都会检查下一个命令选项，
# 如果这些选项出现在option中，则表示是合法选项，否则不是合法选项。并将这些合法选项保存在 arg 这个变量中
while getopts "d:c:" arg
do
    case $arg in
        d)
            # OPTARG（内置变量） 就是将选项后面的参数保存在这个变量当中
            DEPENDS=$OPTARG
            # ;; 与其他语言中的 break 类似，意思是跳到整个 case 语句的最后
            ;;
        c)
            CMD=$OPTARG
            ;;
        ?)
            echo "unkonw argument"
            exit 1
            ;;
    esac
done

# ${DEPENDS//,/ }把DEPENDS中的,替换为空格
for var in ${DEPENDS//,/ }
do
    host=${var%:*}
    port=${var#*:}
    wait_for $host $port
done

# eval命令相当于把$CMD中的命令执行一次
eval $CMD

# 避免执行完命令之后退出容器
# tail -f /dev/null