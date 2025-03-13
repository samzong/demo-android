# Demo 应用开发指南

## 项目概述

Demo 是一个基于 Jetpack Compose 构建的现代 Android 应用，采用 MVVM 架构和 Clean Architecture 设计原则。本文档提供了项目结构、开发流程、测试方法和打包发布的指南。

## 技术栈

- **UI**: Jetpack Compose + Material 3
- **架构**: MVVM + Clean Architecture
- **依赖注入**: Hilt
- **异步处理**: Kotlin Coroutines + Flow
- **导航**: Jetpack Navigation Compose
- **构建工具**: Gradle

## 项目结构

```
app/
  src/
    main/
      java/com/example/demo/
        data/               # 数据层
          repository/       # 仓库实现
          datasource/       # 数据源（本地、远程）
          models/           # 数据模型
        domain/             # 领域层
          usecases/         # 用例
          models/           # 领域模型
          repository/       # 仓库接口
        presentation/       # 表现层
          screens/          # 屏幕组件
          components/       # 可复用组件
          theme/            # 主题相关
          viewmodels/       # 视图模型
          navigation/       # 导航相关
        di/                 # 依赖注入
        utils/              # 工具类
      res/                  # 资源文件
    test/                   # 单元测试
    androidTest/            # UI 测试
```

## 开发指南

### 添加新功能

1. **领域层**:
   - 在 `domain/models` 中定义领域模型
   - 在 `domain/repository` 中定义仓库接口
   - 在 `domain/usecases` 中实现用例

2. **数据层**:
   - 在 `data/models` 中定义数据模型
   - 在 `data/datasource` 中实现数据源
   - 在 `data/repository` 中实现仓库接口

3. **表现层**:
   - 在 `presentation/viewmodels` 中创建 ViewModel
   - 在 `presentation/screens` 中创建 Compose 屏幕
   - 在 `presentation/navigation` 中更新导航

### 编码规范

1. **命名约定**:
   - 类名: PascalCase (如 `LoginViewModel`)
   - 函数名: camelCase (如 `onLoginClick`)
   - 常量: UPPER_SNAKE_CASE (如 `MAX_RETRY_COUNT`)

2. **Compose 组件**:
   - 组件函数使用 PascalCase (如 `LoginScreen`)
   - 内部组件使用 private 修饰符
   - 使用 Preview 注解预览组件

3. **状态管理**:
   - 使用不可变状态 (immutable state)
   - 使用 StateFlow 或 State 暴露状态
   - 使用事件处理函数修改状态

### 依赖注入

使用 Hilt 进行依赖注入:

```kotlin
// 提供依赖
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRepository(): Repository {
        return RepositoryImpl()
    }
}

// 注入依赖
@HiltViewModel
class MyViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    // ...
}
```

## 测试指南

### 单元测试

单元测试位于 `src/test/` 目录下，使用 JUnit 4 框架。

```kotlin
@Test
fun `test login validation`() {
    val viewModel = LoginViewModel()
    viewModel.onUsernameChange("user")
    viewModel.onPasswordChange("pass")
    viewModel.onLoginClick()
    
    val state = viewModel.uiState.value
    assertTrue(state.isLoginSuccessful)
}
```

### UI 测试

UI 测试位于 `src/androidTest/` 目录下，使用 Compose 测试框架。

```kotlin
@Test
fun loginScreen_displaysErrorOnEmptyCredentials() {
    composeTestRule.setContent {
        LoginScreen(onLoginSuccess = {})
    }
    
    composeTestRule.onNodeWithText("登录").performClick()
    composeTestRule.onNodeWithText("用户名或密码不正确").assertIsDisplayed()
}
```

### 运行测试

- 运行单元测试: `./gradlew test`
- 运行 UI 测试: `./gradlew connectedAndroidTest`

## 打包指南

### 构建变体

项目支持以下构建变体:

- **debug**: 开发环境，启用调试功能
- **release**: 生产环境，优化性能

### 构建 APK

```bash
# 构建 Debug APK
./gradlew assembleDebug

# 构建 Release APK
./gradlew assembleRelease
```

生成的 APK 文件位于 `app/build/outputs/apk/` 目录下。

### 构建 AAB (Android App Bundle)

```bash
./gradlew bundleRelease
```

生成的 AAB 文件位于 `app/build/outputs/bundle/release/` 目录下。

## 版本控制

项目使用 Git 进行版本控制，遵循以下分支策略:

- **main**: 稳定版本
- **develop**: 开发分支
- **feature/xxx**: 功能分支
- **bugfix/xxx**: 修复分支

## 持续集成

可以使用 GitHub Actions 或 Jenkins 设置 CI/CD 流程:

1. 代码提交时运行测试
2. 合并到 develop 分支时构建 Debug APK
3. 合并到 main 分支时构建 Release APK

## API 集成

后续将集成 OpenAPI 接口，集成步骤:

1. 在 `data/datasource/remote` 中创建 API 服务接口
2. 在 `data/repository` 中实现仓库，调用 API 服务
3. 更新 ViewModel 使用仓库获取数据

## 问题排查

常见问题及解决方法:

1. **构建失败**: 检查 Gradle 版本和依赖项
2. **Compose 预览不显示**: 确保使用兼容的 Kotlin 和 Compose 版本
3. **Hilt 注入失败**: 检查模块安装和提供方法

## 资源

- [Jetpack Compose 文档](https://developer.android.com/jetpack/compose)
- [Material 3 设计指南](https://m3.material.io/)
- [Hilt 依赖注入](https://developer.android.com/training/dependency-injection/hilt-android)
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) 