import fetchCampaign from "@/api/campaign/fetchCampaign";
import CampaignForm from "@/components/ui/forms/CampaignForm";

export default async function UpdateCampaignForm({ params }) {
    const { id } = await params;
    const campaign = await fetchCampaign(id);

    return (
        <CampaignForm initialData={campaign} />
    )
}
