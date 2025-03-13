# Demo 应用

一个基于 Jetpack Compose 和 Material 3 构建的现代 Android 应用示例。

## 功能特点

- 使用 Jetpack Compose 构建的现代 UI
- 基于 Material 3 设计系统
- 采用 MVVM 架构和 Clean Architecture 设计原则
- 使用 Hilt 进行依赖注入
- 使用 Kotlin Coroutines 和 Flow 进行异步操作
- 使用 Jetpack Navigation Compose 进行导航

## 项目结构

项目采用 Clean Architecture 分层架构:

- **表现层 (Presentation)**: 包含 UI 组件、视图模型和导航
- **领域层 (Domain)**: 包含业务逻辑、用例和仓库接口
- **数据层 (Data)**: 包含仓库实现、数据源和数据模型

## 开始使用

### 前提条件

- Android Studio Arctic Fox (2021.3.1) 或更高版本
- JDK 17
- Android SDK 34
- Kotlin 1.9.0 或更高版本

### 安装

1. 克隆仓库:
   ```
   git clone https://github.com/yourusername/demo.git
   ```

2. 在 Android Studio 中打开项目

3. 同步 Gradle 文件

4. 运行应用

## 开发

详细的开发指南请参阅 [DEVELOPMENT.md](DEVELOPMENT.md)。

## 许可证

本项目采用 MIT 许可证 - 详情请参阅 LICENSE 文件 