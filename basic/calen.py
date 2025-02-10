import calendar

# Créer un objet de calendrier
cal = calendar.TextCalendar(calendar.SUNDAY)  # Vous pouvez spécifier le premier jour de la semaine (ici, dimanche)

# Afficher le mois d'une année spécifique
year = 2003
month = 8
cal_str = cal.formatmonth(year, month)
print(f"Calendrier pour {calendar.month_name[month]} {year}:\n{cal_str}")

# Afficher le calendrier d'une année entière
year = 2003
cal_str = cal.formatyear(year, 2, 1, 1, 3)  # Format année sur 3 colonnes, 1 colonne pour chaque trimestre
print(f"Calendrier pour l'année {year}:\n{cal_str}")