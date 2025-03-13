#!/usr/bin/env python3
import os
import base64
from PIL import Image, ImageDraw
import io

# 确保目录存在
for dpi in ['mdpi', 'hdpi', 'xhdpi', 'xxhdpi', 'xxxhdpi']:
    os.makedirs(f'app/src/main/res/mipmap-{dpi}', exist_ok=True)

# 创建不同尺寸的图标
sizes = {
    'mdpi': 48,
    'hdpi': 72,
    'xhdpi': 96,
    'xxhdpi': 144,
    'xxxhdpi': 192
}

# 创建一个简单的圆形图标
def create_icon(size):
    img = Image.new('RGBA', (size, size), (0, 0, 0, 0))
    draw = ImageDraw.Draw(img)
    
    # 绘制一个蓝色圆形
    draw.ellipse((0, 0, size, size), fill=(63, 81, 181, 255))
    
    return img

# 为每个分辨率创建图标
for dpi, size in sizes.items():
    icon = create_icon(size)
    icon.save(f'app/src/main/res/mipmap-{dpi}/ic_launcher.png')
    icon.save(f'app/src/main/res/mipmap-{dpi}/ic_launcher_round.png')

print("图标创建完成") 