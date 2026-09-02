# Testing

## Atlassian PR merge notification

The workflow `.github/workflows/notify-atlassian-on-pr-merge.yml` sends a webhook
notification to an Atlassian Automation flow whenever a pull request is merged.

To enable it, add a repository secret named `ATLASSIAN_WEBHOOK_URL` containing the
Atlassian Automation Incoming Webhook URL, under
**Settings → Secrets and variables → Actions**.
