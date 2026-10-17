# Keywi companion plan

Status: design proposal; no exported service or cross-app API is shipped yet.

TamoTank owns pet state, simulation, configuration, and its launcher.
Keywi owns zone layout, minimum key sizes, quick-swap controls, and host lifecycle.
A future versioned companion contract must be implemented and tested in both apps.

- Targeted installed-provider discovery through a declared service intent.
- Capability handshake: protocol version, supported wide/tall viewports, minimum
  dimensions, and a configuration activity.
- One shared habitat regardless of top, side, bottom, or standalone presentation.
- Keywi cycles top → side → bottom → off and skips unsupported placements.
- Placement and size are remembered per gem cut; content selection is separate.
- No entered text, clipboard, surrounding text, or InputConnection is shared.
  Optional anonymous activity pulses must be suppressed in sensitive fields.
- Versioned scene snapshots and bounded interactions; agree on a renderer before
  treating another APK's Compose UI as embeddable.
- Visibility-driven work, bounded update frequency, disconnect fallback, explicit
  user selection and provider validation.
- Widgets use a separate adapter; free-floating Android overlays are a later feature.

Next integration milestone: extract a UI-independent habitat engine and state
snapshot schema; then add the provider transport plus a Keywi test host together.
Do not advertise protocol compatibility before end-to-end connection tests pass.
