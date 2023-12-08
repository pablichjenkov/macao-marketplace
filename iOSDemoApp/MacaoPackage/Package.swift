// swift-tools-version: 5.9
// The swift-tools-version declares the minimum version of Swift required to build this package.

import PackageDescription

let package = Package(
    name: "MacaoPackage",
    products: [
        // Products define the executables and libraries a package produces, making them visible to other packages.
        .library(name: "MacaoPackage", targets: ["MacaoPackage"]),
    ],
    dependencies: [
        .package(
            url: "https://github.com/firebase/firebase-ios-sdk.git", from: "10.19.0"),
    ],
    targets: [
        .target(
            name: "MacaoPackage",
            dependencies: [
                .product(name: "FirebaseAuth", package: "firebase-ios-sdk"),
            ]),
        .testTarget(
            name: "MacaoPackageTests",
            dependencies: ["MacaoPackage"])
    ]
)
