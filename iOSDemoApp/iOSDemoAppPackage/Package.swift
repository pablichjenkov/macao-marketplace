// swift-tools-version: 5.9
// The swift-tools-version declares the minimum version of Swift required to build this package.

import PackageDescription

let package = Package(
    name: "iOSDemoAppPackage",
    products: [
        // Products define the executables and libraries a package produces, making them visible to other packages.
        .library(
            name: "iOSDemoAppPackage",
            targets: ["iOSDemoAppPackage"]),
    ],
    dependencies: [
        .package(
            url: "https://github.com/firebase/firebase-ios-sdk.git", from: "10.19.0"),
        // Even though we set Xcode to consume our package locally, it is needed
        // to indicate SPM where is the url where this package is located.
        // Otherwise Xcode/SPM starts complaining about not finding the package.
        .package(url: "https://github.com/pablichjenkov/firebase-kmp.git", branch: "main"),
    ],
    targets: [
        .target(
            name: "iOSDemoAppPackage",
            dependencies: [
                .product(
                    name: "FirebaseAuth",
                    package: "firebase-ios-sdk"
                ),
                .product(
                    name: "FirebaseAuthKmp",
                    package: "firebase-kmp"
                ),
            ]
        ),
        .testTarget(
            name: "iOSDemoAppPackageTests",
            dependencies: ["iOSDemoAppPackage"]
        ),
    ]
)
