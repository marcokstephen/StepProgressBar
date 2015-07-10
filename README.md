# StepProgressBar
An Android progress bar that uses coloured circles or drawables, and can be "stepped through" by the user

![](http://i.imgur.com/kGQZL4H.png)
![](http://i.imgur.com/K5TVIE5.png)
![](http://i.imgur.com/UFVWpPN.png)
## Usage
    <com.marcok.stepprogressbar.StepProgressBar
        android:layout_centerVertical="true"
        android:layout_height="0dp"
        android:layout_width="wrap_content"
        android:id="@+id/stepProgressBar"
        app:cumulativeDots="false"
        app:activeDotIndex="0"
        app:activeDotColor="@color/material_deep_teal_500"
        app:inactiveDotColor="@color/material_blue_grey_800"
        app:activeDotIcon="@drawable/checkmark"
        app:inactiveDotIcon="@drawable/wrongmark"
        app:numberDots="6"
        app:dotSize="20dp"
        app:spacing="20dp"/>
* If dot icons are provided, they override dot colors
* Layout height is irrelevant and determined by the `dotSize`
* To move to the next dot or previous dot:
````
StepProgressBar mStepProgressBar = (StepProgressBar)findViewById(R.id.stepProgressBar);
mStepProgressBar.next();
mStepProgressBar.previous();
````
* To set the dots to be cumulative (all dots <= the specified index will show as active), call `setCumulativeDots(true)`
* Calling `setCurrentProgressDot(-1)` will prevent any dots from showing as active. Any lower values will throw `IndexOutOfBoundsExeption`.
* Other methods:
````
setCurrentProgressDot()
setNumDots()
setActiveColor()
setInactiveColor()
setActiveDrawable()
setInactiveDrawable()
````

## Changelog
* [changelog.txt](changelog.txt)
