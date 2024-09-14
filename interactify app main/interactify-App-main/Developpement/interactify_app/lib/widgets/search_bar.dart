import 'package:flutter/material.dart';
import 'package:flutter_gen/gen_l10n/app_localizations.dart';

class SearchingBar extends StatelessWidget {
  final TextEditingController controllerTextEditing;
  final void Function(String) runFilter;

  const SearchingBar({super.key, required this.controllerTextEditing, required this.runFilter});

  @override
  Widget build(BuildContext context) {
        final localizations = AppLocalizations.of(context)!;

    return Row(
      children: [
        Expanded(
          child: Container(
            height: 50,
            decoration: BoxDecoration(
              color: Colors.lightBlue.withOpacity(0.2),
              borderRadius: BorderRadius.circular(25),
            ),
            child: Row(
              children: [
                const Padding(
                  padding: EdgeInsets.only(left: 16.0),
                  child: Icon(
                    Icons.search,
                    color: Color.fromARGB(137, 56, 55, 55),
                  ),
                ),
                Expanded(
                  child: TextField(
                    onChanged: runFilter,
                    controller: controllerTextEditing,
                    textAlign: TextAlign.start,
                    style: const TextStyle(
                      color: Colors.black,
                      fontSize: 18,
                    ),
                    decoration: const InputDecoration(
                      hintText: "search",
                      hintStyle: TextStyle(
                        color: Color.fromARGB(137, 56, 55, 55),
                        fontSize: 18,
                      ),
                      border: InputBorder.none,
                    ),
                  ),
                ),
              ],
            ),
          ),
        ),
        const SizedBox(width: 8.0),
        GestureDetector(
          onTap: () {
            controllerTextEditing.clear();
          },
          child: Container(
            child: Padding(
              padding: const EdgeInsets.all(8.0),
              child: IconButton(
                icon: const Icon(Icons.close),
                onPressed: () {
                  controllerTextEditing.clear();
                },
              ),
            ),
          ),
        ),
      ],
    );
  }
}
