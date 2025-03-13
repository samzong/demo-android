#!/bin/bash

# 创建临时目录
mkdir -p temp_icons

# 创建一个简单的彩色图标
convert -size 512x512 xc:none -fill "#3F51B5" -draw "circle 256,256 256,100" temp_icons/base_icon.png

# 创建不同分辨率的图标
convert temp_icons/base_icon.png -resize 48x48 app/src/main/res/mipmap-mdpi/ic_launcher.png
convert temp_icons/base_icon.png -resize 72x72 app/src/main/res/mipmap-hdpi/ic_launcher.png
convert temp_icons/base_icon.png -resize 96x96 app/src/main/res/mipmap-xhdpi/ic_launcher.png
convert temp_icons/base_icon.png -resize 144x144 app/src/main/res/mipmap-xxhdpi/ic_launcher.png
convert temp_icons/base_icon.png -resize 192x192 app/src/main/res/mipmap-xxxhdpi/ic_launcher.png

# 创建圆形图标
convert temp_icons/base_icon.png -resize 48x48 app/src/main/res/mipmap-mdpi/ic_launcher_round.png
convert temp_icons/base_icon.png -resize 72x72 app/src/main/res/mipmap-hdpi/ic_launcher_round.png
convert temp_icons/base_icon.png -resize 96x96 app/src/main/res/mipmap-xhdpi/ic_launcher_round.png
convert temp_icons/base_icon.png -resize 144x144 app/src/main/res/mipmap-xxhdpi/ic_launcher_round.png
convert temp_icons/base_icon.png -resize 192x192 app/src/main/res/mipmap-xxxhdpi/ic_launcher_round.png

# 清理临时文件
rm -rf temp_icons

echo "图标创建完成" 