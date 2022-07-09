# sample-for-job
sample for job



Please note that this project is done in MVVM Clean Architecture
Being myself lazy, I did not created multiple modules for base and core modules instead I created the public classes there.

API Service - > Files related to REST APIS : Interface and service that implements it

BASE -> all the core helper classes that makes proud of being lazy copied from https://github.com/android10/Android-CleanArchitecture-Kotlin
Please note: Base can be designed in such a way that it can be included in all the projects i.e basic library call for complete project setup

core -> core modules that includes the entity classes and how the repository is maintained either from api or from db. I remained lazy at this so didn't do.
interactors here are somewhat interesting that makes complete module hidden displaying only what is required.

main module including views and viewmodels.

