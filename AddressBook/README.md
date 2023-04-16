# Introduction

页面1，显示通讯录列表，要求使用RecycleView开发

页面2，通讯录新增或修改页面

数据模型：

1. 通讯信息：包含姓名，电话，地址，均为字符串

跳转逻辑：

通讯录列表每一行包含一个编辑按钮与删除按钮，点击编辑按钮，跳转到页面
2. 进行编辑，点击删除按钮，弹出是否删除确认对话框，如点击确认，则删除该行，如点击取消则关闭确认对话框

跳转页面2为编辑状态时，需显示原通讯信息

页面1包含单独的新增信息按钮，点击跳转到页面1进行新增操作

页面2通过文本编辑框进行编辑，根据跳转模式不同，包含一个确认按钮，依据跳转逻辑分别显示“更新”，“新增”。

页面2包含一个返回或取消按钮，点击返回后不对当前通讯录列表做任何更改。


# Resources
- [https://developer.android.google.cn/guide/topics/ui/layout/recyclerview?hl=zh-cn](https://developer.android.google.cn/guide/topics/ui/layout/recyclerview?hl=zh-cn)
- [https://github.com/android/views-widgets-samples/tree/main/RecyclerViewKotlin/](https://github.com/android/views-widgets-samples/tree/main/RecyclerViewKotlin/)