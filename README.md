# TamoTank 🐟

A standalone Android aquarium, extracted from the Fishwi experiment in Keywi.
Install alongside Keywi; TamoTank has its own application ID and launcher.

## First build
Seven fish (Pixel, Miso, Dot, Lemon, Guppy, Peach, Mint), personality-driven swimming,
food seeking, growth, bubbles, play, and persistent local habitat state.
Tap or swipe the tank, or use the controls above it. Wide and tall previews share
one habitat. Animation pauses while the activity is hidden.

## Build and install
JDK 17 and Android SDK 35:
```sh
./gradlew :app:assembleDebug :app:lintDebug
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

Actions builds an installable debug APK on pushes and pull requests. Download
the TamoTank-debug artifact from the latest successful run and unzip it.
CI retains its debug signing key in an Actions cache for update continuity.
If that cache is evicted, uninstalling the previous debug build may be necessary.
Production signing is a separate future step.

## Keywi integration status
This extraction runs independently. It does **not yet** appear inside Keywi.
No keyboard, accessibility, network, or overlay permission is requested.
See [the companion plan](docs/companion-plan.md) for the planned API and zones.
Existing Fishwi saves belong to the old APK and are not automatically transferred.

## Provenance
Extracted from Keywi-Fishwi at f272b928001850c4181c773d767e1a7038965316.
See NOTICE and LICENSE (AGPL-3.0-or-later). The original branch remains intact.
