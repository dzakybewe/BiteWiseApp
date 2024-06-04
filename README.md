An app that can help you choose restaurant easier

Project Structure:
- bitewise

  -- /data

      --- /remote

          Berhubungan sama networking, api requests, dll

      --- /repository

          jembatan buat ngambil data antara viewmodel sama data source (api atau database)

  -- /ui

      --- /components

          buat bikin widget/komponen yang reusable (misal button dll)

      --- /navigation

          related to all navigation things
        
      --- /screens/{screen name}

          related to all screen things (misal di homescreen ada HomeScreen dan HomeViewModel)

      --- /theme

          Ya buat ngurus theme, color, dll
