import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() => runApp(const MyApp());

const platform = MethodChannel('nfc.payment/channel');

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) => MaterialApp(
        title: 'NFC Payment Demo',
        home: const CardInputPage(),
      );
}

class CardInputPage extends StatefulWidget {
  @override
  _CardInputPageState createState() => _CardInputPageState();
}

class _CardInputPageState extends State<CardInputPage> {
  final _formKey = GlobalKey<FormState>();
  String cardNumber = '', expiry = '', cvv = '';

  void _saveCard() {
    if (_formKey.currentState!.validate()) {
      _formKey.currentState!.save();
      ScaffoldMessenger.of(
        context,
      ).showSnackBar(SnackBar(content: Text('Card Saved (Simulated)')));
    }
  }

  Future<void> _startNFC() async {
    try {
      await platform.invokeMethod('startNFC');
    } on PlatformException catch (e) {
      print("Error: ${e.message}");
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("NFC Payment Setup")),
      body: Padding(
        padding: const EdgeInsets.all(16),
        child: Form(
          key: _formKey,
          child: Column(
            children: [
              TextFormField(
                decoration: InputDecoration(labelText: 'Card Number'),
                onSaved: (val) => cardNumber = val!,
              ),
              TextFormField(
                decoration: InputDecoration(labelText: 'Expiry'),
                onSaved: (val) => expiry = val!,
              ),
              TextFormField(
                decoration: InputDecoration(labelText: 'CVV'),
                obscureText: true,
                onSaved: (val) => cvv = val!,
              ),
              SizedBox(height: 20),
              ElevatedButton(
                onPressed: _saveCard,
                child: Text("Save Card (Demo)"),
              ),
              SizedBox(height: 20),
              ElevatedButton(
                onPressed: _startNFC,
                child: Text("Start NFC Payment"),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
