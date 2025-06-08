# NFC Payment Demo

This project demonstrates a simple Flutter application that uses Android's Host Card Emulation (HCE) to respond to NFC payment requests. The app lets you enter dummy card details and start an NFC session through a platform channel.

## Prerequisites
- Flutter 3.7 or later
- Android device with NFC and HCE support

## Building and Running
1. Get dependencies:
   ```
   flutter pub get
   ```
2. Run on an Android device or emulator with NFC:
   ```
   flutter run
   ```
3. Execute tests:
   ```
   flutter test
   ```

When you tap **Start NFC Payment**, the native Android code checks for NFC availability and activates the `MyHostApduService` which replies to a simple APDU SELECT command.

## License

This project is licensed under the [MIT License](LICENSE).
