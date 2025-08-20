# Defect Tool Box 

## DEPRECATION

**Notice: This project is no longer maintained or supported.**

**Deprecation Reason**: The project has been deprecated due to AI ✨, you can do it in a prompt (if you have internet connection, or on AI model on local machine).

**Archive**: This repository will be archived and remain available for historical purposes.

## Overview

DefectToolBox was a personal tool to analyze text for prefixed-base Ids, of two set and diff them. 
It was developed and maintained during my first job, for that job. And it also help me in the later job.

I've used to work with multiple tracking system for bugs, and all of them have Id for each item, which the team called `Defect`. 
In the end of the day, I need to report the changes in bug status comparing with yesterday one, and ... 
most of my friends using spreadsheet for counting thems. I use this for myself first and share it away in the project.

## What and how it worked

Simple, one text input for prefix, two text areas for inputs, one button to get output as three-box of Ids.  

The `DefectToolBox` class, along with its FXML files, provides a UI for the tool box 

The `DefectSet` class provides the following features:

*   **Defect detection**: It can load a string input, tokenize numbers, and detect defects based on a given prefix and maximum value.
*   **Set operations**: It supports set operations such as subtraction (`subtract`) and intersection (`intersect`) with another `DefectSet` object.
*   **Defect listing**: It can list all detected defects in a sorted order (`listAll`).
*   **Defect counting**: It can return the number of detected defects (`size`).
*   **Prefix-based defect detection**: It can detect defects based on a given prefix, allowing for detection of defects with a specific pattern.

## 😌 Released

By update this note, I feel relived to let it away. Thanks Github for keeping this as my own first helpful tool. 
