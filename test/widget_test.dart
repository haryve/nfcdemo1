import 'package:flutter_test/flutter_test.dart';
import 'package:nfc_payment_demo/main.dart';

void main() {
  testWidgets('Initial screen shows NFC Payment Setup', (tester) async {
    await tester.pumpWidget(const MyApp());

    expect(find.text('NFC Payment Setup'), findsOneWidget);
  });
}
