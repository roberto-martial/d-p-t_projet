// ignore_for_file: file_names

import 'package:flutter/material.dart';
import 'package:interactify_app/Pages/list_chats_page.dart';

class Head extends StatelessWidget implements PreferredSizeWidget {
  const Head({super.key});

  @override
  Size get preferredSize => const Size.fromHeight(kToolbarHeight);

  @override
  Widget build(BuildContext context) {
    return AppBar(
      title: const Text(
        'Interactify',
        style: TextStyle(
            fontFamily: 'Yesteryear', color: Colors.black, fontSize: 30),
      ),
      automaticallyImplyLeading: false,
      actions: <Widget>[
        IconButton(
          icon: Image.asset('assets/images/messenger.png'),
          onPressed: () {
            Navigator.pushNamed(context, ListChats.routeName);
          },
        ),
      ],
      backgroundColor: Color.fromARGB(13, 46, 163, 248),
    );
  }
}
