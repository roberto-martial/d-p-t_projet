import 'package:flutter/material.dart';

class CircleButton extends StatefulWidget {
  final int position;

  const CircleButton({super.key, required this.position});

  @override
  State<CircleButton> createState() => _CircleButtonState();
}

class _CircleButtonState extends State<CircleButton> {
  int _currentNumber = 1;

  void _onNumberEntered(int number) {
    setState(() {
      _currentNumber = number;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Rond Bleu'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceAround,
              children: <Widget>[
                _circle(1),
                _circle(2),
                _circle(3),
              ],
            ),
          ],
        ),
      ),
    );
  }

  //Creation du widget des cercles
  Widget _circle(int number) {
    return GestureDetector(
      onTap: () {
        _onNumberEntered(number);
      },
      child: Container(
        width: 20,
        height: 20,
        decoration: BoxDecoration(
          shape: BoxShape.circle,
          color: _currentNumber == number? Colors.blue : Colors.grey,
        ),
      ),
    );
  }
}