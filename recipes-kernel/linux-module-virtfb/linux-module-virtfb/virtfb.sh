#!/bin/sh
# disable LCD blanking
echo -e -n '\033[9]' > /dev/tty0
# disable cursor
echo -e -n '\033[?25l' > /dev/tty0

fbset -g 1024 768 1024 1536 32
modprobe virtual_fb

fbset -fb /dev/fb5 -g 800 480 800 480 32
fbset -fb /dev/fb6 -g 320 240 320 240 32
fbset -g 1024 768 1024 2304 32
