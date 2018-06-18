# Divider(ItemDecoration) for RecyclerView

[![Download](https://api.bintray.com/packages/sheldonchen0922/plugin/recyclerview-divider/images/download.svg)](https://bintray.com/sheldonchen0922/plugin/recyclerview-divider/_latestVersion)

能够帮助实现RecyclerView分割线效果的ItemDecoration<br>
支持RecyclerView线性，网格，瀑布流布局

Android library helps you to draw divider(ItemDecoration) between items in RecyclerView.

## Support
- LinearLayoutManager
  - Vertical
  - Horizontal
- GridLayoutManager
  - Vertical
  - Horizontal
- StaggeredGridLayoutManager

## Image
<div>
  <img src="gif/S80417-153516.jpg" width = "200" height = "350" hspace = "15"/>
  <img src="gif/S80417-154641.jpg" width = "200" height = "350" hspace = "15"/>
  <img src="gif/S80427-151850.jpg" width = "200" height = "350" hspace = "15"/>
</div>

## Gradle
Add the dependency
```
dependencies {
    compile 'com.aches.plugins:recyclerview-divider:1.0.1'

    // 3.0
    // implementation 'com.aches.plugins:recyclerview-divider:1.0.1'
}
```

## How To Use
分割线支持设置Color以及Drawable, 也可以通过实现接口[IDividerPainter](https://github.com/AchesKkk/ItemDecorations/blob/master/recyclerview-divider/src/main/java/com/sheldonchen/itemdecorations/painter/base/IDividerPainter.java)进行自定义分割线.
```
Drawable dividerDrawable = getResources().getDrawable(R.drawable.shape_divider_vertical);
int dividerColor = getResources().getColor(R.color.divider);
  
LinearLayoutDivider divider = new LinearLayoutDivider.Builder()
        .setOrientation(LinearLayoutManager.VERTICAL)
        .drawFirstDivider(true)
        .drawLastDivider(true)
        .setStartPadding(0)
        .setEndPadding(0)
// color.
        .setDividerColor(dividerColor)
// drawable.
//      .setDividerDrawable(dividerDrawable)
// 自定义IDividerPainter.
//      .setPainter(new DrawablePainter(dividerDrawable))
//      .setPainter(new ColorIntPainter(dividerColor))
        .setDividerThickness(10)// 设置分割线的厚度.
        .build();
recyclerview.addItemDecoration(divider);
```
```
Drawable dividerDrawable = getResources().getDrawable(R.drawable.shape_divider);
int dividerColor = getResources().getColor(R.color.divider);

GridLayoutDivider divider = new GridLayoutDivider.Builder()
        .setOrientation(LinearLayoutManager.VERTICAL)
        .drawTopEdgeDivider(true)
        .drawBottomEdgeDivider(true)
        .drawLREdgesDivider(true)
// color.
        .setDividerColor(dividerColor)
        .setSideDividerColor(dividerColor)
// drawable.
//      .setDividerDrawable(dividerDrawable)
//      .setSideDividerDrawable(sideDividerDrawable)
// 自定义IDividerPainter.
//      .setPainter(new ColorIntProvider(dividerColor))
//      .setSidePainter(new DrawableProvider(dividerDrawable))
        .setDividerThickness(15)
        .setSideDividerThickness(20)
        .build();
recyclerview.addItemDecoration(divider);
```


# License
```
Copyright (C) 2018 AchesKkk

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
