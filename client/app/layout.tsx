import type { Metadata } from "next";
import "./globals.css";

export const metadata: Metadata = {
  title: "P2P File Sharer",
  description: "Share files easily",
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <html lang="en">
      <body>{children}</body>
    </html>
  )
}
