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
        // Bellow consumes firebase-kmp package from github instead of our local.
        //.package(url: "https://github.com/pablichjenkov/firebase-kmp.git", branch: "main"),
    ],
    targets: [
        .binaryTarget(
            name: "composeApp",
            path: "../../composeApp/build/XCFrameworks/debug/composeApp.xcframework"
        ),
        .target(
            name: "iOSDemoAppPackage",
            dependencies: [
                .byName(name: "composeApp"),
                .product(
                    name: "FirebaseAuth",
                    package: "firebase-ios-sdk"
                ),
                //.product(
                //    name: "FirebaseAuthKmp",
                //    package: "firebase-kmp"
                //),
            ]
        ),
        .testTarget(
            name: "iOSDemoAppPackageTests",
            dependencies: ["iOSDemoAppPackage"]
        ),
    ]
)
