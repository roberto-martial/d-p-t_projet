// ignore_for_file: file_names
//Ajout des packages necessaires  
import 'package:flutter/material.dart';

//Definition de la classe
class CircleButton extends StatelessWidget {
  final int position;

  const CircleButton({super.key, required this.position});

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
      child: Container(
        width: 20,
        height: 20,
        decoration: BoxDecoration(
          shape: BoxShape.circle,
          color: position == number? Colors.blue : Colors.grey,
        ),

          ),
    );
  }
}