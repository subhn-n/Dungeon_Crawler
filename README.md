# 🎮 Dungeon Crawler

> **A new expedition awaits!** 🗺️

Dungeon Crawler is a Jav<a 2D retro game currently under development.

A playable release is now available for **Windows**, **Linux**, and **macOS**. The project is still evolving, so expect new features, bug fixes, balancing changes, and probably a few unexpected creatures hiding in the dungeon. 🏰

**Here are the first seconds of the gameplay**

<table>
  <tr>
    <td width="600">
      <video src="https://github.com/user-attachments/assets/ca3ad14d-c658-4d8b-a88b-87e9b68702c1" controls></video>
    </td>
  </tr>
</table>


    
---

# 📦 Download

Go to the **Releases** section of this repository and download the version matching your operating system.

- 🪟 `Dungeon-Crawler-vX.X.X-Windows.zip`
- 🐧 `Dungeon-Crawler-vX.X.X-Linux.tar.gz`
- 🍎 `Dungeon-Crawler-vX.X.X-macOS.zip`

Replace `vX.X.X` with the latest available version (for example `v1.1.1`).

---

# 🚀 Launch the game

## 🪟 Windows

1. Download the Windows ZIP archive.
2. Extract it.
3. Open the extracted `Dungeon-Crawler` folder.
4. Double-click **Dungeon-Crawler.exe**.

---

## 🐧 Linux

Extract the archive:

```bash
tar -xzf Dungeon-Crawler-vX.X.X-Linux.tar.gz
```

Open the extracted folder:

```bash
cd Dungeon-Crawler
```

Launch the game:

```bash
./bin/Dungeon-Crawler
```

If Linux refuses to execute it:

```bash
chmod +x bin/Dungeon-Crawler
./bin/Dungeon-Crawler
```

---

## 🍎 macOS

1. Download the macOS ZIP archive.
2. Extract it.
3. Open **Dungeon-Crawler.app**.

---

# ☕ Is Java required?

**No.**

The Windows, Linux and macOS releases already include their own Java Runtime.

Simply extract the archive and launch the game.

There is **no need to install Java**.

---

# ⚠️ Development status

Dungeon Crawler is still an ongoing project.

You may encounter:

- 🐞 Bugs
- ⚔️ Unfinished mechanics
- 🎨 Placeholder graphics
- ⚖️ Balance changes
- 👾 Unexpected dungeon behaviour
- 📦 Treasure chests containing disappointingly ordinary loot

Feedback is always welcome!

If you find a bug, please open an **Issue** and include:

- your operating system
- the release version
- what happened
- what you expected
- how to reproduce the problem

---

# 🔐 Security notice

The application is **not digitally signed yet**.

Because of this, Windows Defender SmartScreen or macOS Gatekeeper may warn that the publisher is unknown.

This is expected for personal open-source projects.

Please download the game **only from the official GitHub Releases page** of this repository.

Digital code signing will be added in a future release.

---

# 🛠️ Build from source

Clone the repository and run:

```bash
./gradlew clean build
```

The generated JAR will be located in:

```text
build/libs/Dungeon-Crawler.jar
```

To launch it manually:

```bash
java -jar build/libs/Dungeon-Crawler.jar
```

---

# ❤️ Thank you!

Thank you for trying **Dungeon Crawler**!
