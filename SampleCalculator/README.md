# SampleCalculator

嗨，各位，最近又做了簡單的東西分享一下，主要也是因為開發中所需也順便做成Library.

那麼這東西其實是非常簡單的，但為了做成一個View方便呼叫，因此就麻煩了一點.

先來介紹一下主打功能呀!

大家如下圖所示，此東西可以讓大家選擇自己要的背景、文字顏色，其餘皆是固定的(有製作自適應大小)，

下面那邊大家可以看到換算結果的文字，那邊是外加的，因此並不包括在Library，所以靈活性較高，

因此也提供Listener讓你們可以得知目前運算的數字與運算子。

##如何使用 How to use

###Gradle
```gradle
compile project(':samplecalculator')
```

###XML
```xml
<com.kuo.samplecalculator.CalculatorView
        android:id="@+id/calculatorView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="vertical"></com.kuo.samplecalculator.CalculatorView>
```
###JAVA
```java
setOnItemClickListener(OnItemClickListener);
setColors(int id, int color);
setColors(int id, int color, int background);
```
####setColors ID
```java
int PLUS;
int MINUS;
int TIMES;
int DIVIDED;
int NUMBER;
int CLEAR;
int CLEAR_ALL;
int OK;
```
##View
<img src="https://googledrive.com/host/0B5fOJF9g7N2SNUdTdXpBMG94ck0" width="40%" height="40%">
