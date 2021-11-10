# AppRate

A HMOS library which provides AppRater animation

## Source


## Feature
AppRate for Android is a library that allows your users to rate your application in a non intrusive way.
 A window with options to rate, remind later or reject is displayed according to your configuration.

<img src="https://github.com/priyankabb153/RainyView/blob/master/screenshots/rainyview2.gif" width="356">


## Dependency
1. For using apprater module in sample app, include the source code and add the below dependencies in entry/build.gradle to generate hap/support.har.
```groovy
	dependencies {
		implementation project(':apprater')
                implementation fileTree(dir: 'libs', include: ['*.har'])
                testImplementation 'junit:junit:4.13'
	}
```
2. For using apprater in separate application using har file, add the har file in the entry/libs folder and add the dependencies in entry/build.gradle file.
```groovy
	dependencies {
		implementation fileTree(dir: 'libs', include: ['*.har'])
		testImplementation 'junit:junit:4.13'
	}
```

## Usage

#### In layout.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<DirectionalLayout
    xmlns:ohos="http://schemas.huawei.com/res/ohos"
    ohos:height="match_parent"
    ohos:width="match_parent"
    ohos:alignment="center"
    ohos:orientation="vertical">

</DirectionalLayout>
```

#### In code

super.onStart(intent);
        setUIContent(ResourceTable.Layout_ability_main);
        new AppRater(this)
                .setMinDays(0)
                .setMinLaunches(0)
                .setAppTitle("My Title")
                .init();

```

## License
```
Copyright [yyyy] [name of copyright owner]

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